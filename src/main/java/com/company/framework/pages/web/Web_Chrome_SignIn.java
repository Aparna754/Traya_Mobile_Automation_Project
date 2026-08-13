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
        releaseStaleProfileLock(profileDir);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + profileDir);
        options.addArguments("profile-directory=" + profileName);
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
            String target = Files.readSymbolicLink(lockFile).toString();
            String pid = target.substring(target.lastIndexOf('-') + 1);
            new ProcessBuilder("kill", "-9", pid).start().waitFor();
        } catch (Exception e) {
        }

        for (String lockName : new String[]{"SingletonLock", "SingletonSocket", "SingletonCookie"}) {
            new File(profileDir, lockName).delete();
        }
    }

    private static final String WELCOME_URL = "https://erp.traya.health/welcome";
    public static void navigateToWelcomePage(WebDriver driver) {
        driver.get(WELCOME_URL);
    }

    public static void WebFlow() {

        WebDriver webDriver = createSignedInChromeDriver();
        navigateToWelcomePage(webDriver);
    }
}
