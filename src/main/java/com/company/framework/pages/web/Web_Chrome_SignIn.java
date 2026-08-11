package com.company.framework.pages.web;

import com.company.framework.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Web_Chrome_SignIn {

    // Dedicated Chrome profile so the automated session stays signed in to Google/ERP without
    // touching the tester's everyday Chrome profile, history, or saved passwords - and without
    // the "user data directory is already in use" failure a shared profile hits when the
    // tester's regular Chrome window is open at the same time. Sign in to Google inside this
    // profile once, manually, outside the tests; ChromeDriver then just reuses whatever session
    // cookies are already saved there on every later run.
    private static final String DEFAULT_PROFILE_DIR = System.getProperty("user.home") + "/chrome-automation-profile";

    public static WebDriver createSignedInChromeDriver() {

        String profileDir = ConfigReader.get("web.chromeProfileDir");
        if (profileDir == null || profileDir.isBlank()) {
            profileDir = DEFAULT_PROFILE_DIR;
        }
        String profileName = ConfigReader.get("web.chromeProfileName");
        if (profileName == null || profileName.isBlank()) {
            profileName = "Default";
        }

        // Callers here don't reliably quit() the driver (e.g. leaving the browser open for
        // manual inspection), which leaves Chrome's SingletonLock pointed at a still-running
        // process. Chrome refuses to start a second instance against a locked profile - the
        // new process just exits, surfacing as "session not created: Chrome instance exited".
        // Clearing any stale lock here makes every launch self-healing instead of requiring
        // someone to manually kill the leftover process first.
        releaseStaleProfileLock(profileDir);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + profileDir);
        options.addArguments("profile-directory=" + profileName);

        // chromedriver forces these into every launch by default (visible under
        // chrome://version's Command Line):
        //  - disable-sync: suppresses Chrome's sync/identity UI, so the account avatar/name
        //    never renders as signed in even when the profile's credentials are genuinely there.
        //  - use-mock-keychain / password-store: signs the profile's protected preferences
        //    (the same mechanism that guards signed-in/sync state) with a fake in-memory
        //    keychain instead of the real macOS Keychain a manual Chrome launch uses. The
        //    mismatch makes Chrome treat the profile as "changed outside Chrome" on the next
        //    automated launch and silently reset the protected state - wiping the sign-in
        //    that was just set up manually. Excluding all of these keeps automated and manual
        //    launches consistent so the signed-in state actually survives.
        options.setExperimentalOption("excludeSwitches", List.of("disable-sync", "use-mock-keychain", "password-store"));

        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    private static void releaseStaleProfileLock(String profileDir) {

        Path lockFile = new File(profileDir, "SingletonLock").toPath();
        if (!Files.isSymbolicLink(lockFile)) {
            return;
        }

        try {
            // Chrome writes the lock as a symlink shaped "<hostname>-<pid>".
            String target = Files.readSymbolicLink(lockFile).toString();
            String pid = target.substring(target.lastIndexOf('-') + 1);
            new ProcessBuilder("kill", "-9", pid).start().waitFor();
        } catch (Exception e) {
            // Best-effort: if the PID is already gone or unparseable, falling through to
            // delete the lock files below is still safe.
        }

        for (String lockName : new String[]{"SingletonLock", "SingletonSocket", "SingletonCookie"}) {
            new File(profileDir, lockName).delete();
        }
    }

    private static final String WELCOME_URL = "https://erp.traya.health/welcome";

    /**
     * Navigates an already-created driver (e.g. from createSignedInChromeDriver()) to the ERP
     * welcome page - callers that need the driver afterward (to chain further page actions)
     * should create it themselves and pass it in here, rather than going through WebFlow(),
     * which owns its own driver end-to-end.
     */
    public static void navigateToWelcomePage(WebDriver driver) {
        driver.get(WELCOME_URL);
    }

    public static void WebFlow() {

        WebDriver webDriver = createSignedInChromeDriver();
        navigateToWelcomePage(webDriver);
    }
}
