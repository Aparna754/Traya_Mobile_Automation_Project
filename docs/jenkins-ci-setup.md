# Jenkins CI/CD Setup

The `Jenkinsfile` at the repo root defines the pipeline; this doc covers the one-time setup on
the Jenkins server/agent side that has to happen outside this repo before it will run.

## 1. Agent requirements

The pipeline is written for a **macOS agent** (matches the Android emulator setup already used
locally in this project). The agent needs:

- Java 25, Maven (`mvn -version` must work in the Jenkins agent's shell, not just your interactive shell - see the PATH note below)
- Android SDK with an emulator AVD already created (`Pixel_10_Pro` by default - override via the
  `AVD_NAME` build parameter, or create an AVD with that exact name on the agent)
- Node.js + the Appium CLI: `npm install -g appium`, then `appium driver install uiautomator2`
  (and `appium driver install xcuitest` if you'll run the iOS suites)
- Xcode + command line tools (only required for `testng-ios.xml`/`testng-parallel.xml`)

**PATH gotcha:** Jenkins agents typically run with a minimal, non-login shell environment, which
often does NOT include the same PATH as your interactive terminal (where `adb`/`appium`/`mvn`
already work today). Before wiring up the trigger, run the "Verify Toolchain" stage manually
(or SSH in as the Jenkins agent user) and confirm `java -version`, `mvn -version`, `adb version`,
and `appium --version` all resolve. If `appium` isn't found, check where npm installed it
(`npm config get prefix`) and add that `bin` directory to the `PATH` in the Jenkinsfile's
`environment` block.

## 2. Register the agent with a label

In Jenkins: **Manage Jenkins → Nodes → New Node**, and give it the label `macos-android` (or
change the `agent { label 'macos-android' }` line in the `Jenkinsfile` to whatever label you
actually use).

## 3. Create the pipeline job

- **New Item → Pipeline** (a plain Pipeline job is enough for a single-branch setup; use a
  **Multibranch Pipeline** instead if you want Jenkins to auto-discover and build PRs/branches).
- Under **Pipeline**, set **Definition** to "Pipeline script from SCM":
  - SCM: Git
  - Repository URL: `https://github.com/Aparna754/Traya_Mobile_Automation_Project.git`
  - Branch: `*/main` (or `**` for a Multibranch job)
  - Script Path: `Jenkinsfile`
- Install the required plugins if not already present: **Pipeline**, **Git**, **GitHub**,
  **JUnit** (usually bundled).

## 4. Wire up the GitHub webhook (for the "run on every push/PR" trigger)

The `Jenkinsfile` declares `triggers { githubPush() }`, which needs a webhook configured on the
GitHub repo:

1. On GitHub: repo **Settings → Webhooks → Add webhook**
2. Payload URL: `http://<your-jenkins-host>:8080/github-webhook/` (must be reachable from GitHub -
   use a tunnel like ngrok, or a public/VPN-reachable Jenkins URL, if Jenkins is only on your
   local network)
3. Content type: `application/json`
4. Which events: "Just the push event" (add "Pull requests" too if you set this up as a
   Multibranch Pipeline and want PR builds)
5. In the Jenkins job config, under **Build Triggers**, confirm "GitHub hook trigger for GITScm
   polling" is checked (Pipeline-as-code jobs using `githubPush()` in the Jenkinsfile usually
   pick this up automatically once the GitHub plugin is installed, but verify it after the first
   webhook delivery).

## 5. First run

Trigger a build manually first (**Build with Parameters**) before relying on the webhook, so you
can watch the console output for the emulator boot / Appium startup steps and fix any
agent-specific PATH or AVD-name issues without waiting on a git push.

## What the pipeline does NOT handle

- **Credentials/secrets**: none are needed today (local emulator, local Appium, no cloud device
  farm, no Slack/email). If you later add a device farm or notifications, add those credentials
  via Jenkins' Credentials store - don't hardcode them in the Jenkinsfile.
- **Cloud device farms** (BrowserStack/Sauce Labs/etc.): this pipeline assumes a local emulator on
  the agent. Pointing at a device farm instead means changing `config.properties`'
  `appiumServerUrl` and capabilities, and is a different pipeline shape (no local emulator-boot
  stage needed) - ask if you want this built out.
- **iOS real devices**: `docs/ios-real-device-setup.md` covers the manual signing steps; those
  still need to be done once on the agent before `testng-ios.xml`/`testng-parallel.xml` can pass
  against a real iPhone in CI.
