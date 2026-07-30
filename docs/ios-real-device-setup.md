# iOS Real Device Setup

The framework's config keys for a physical iPhone are in place (`ios.udid`, `ios.xcodeOrgId`,
`ios.xcodeSigningId`, `ios.updatedWDABundleId` in `config.properties`), but getting WebDriverAgent
signed and installed on a real device is a manual, one-time Xcode/Apple-Developer setup that this
framework does not automate. Steps:

## Prerequisites

1. A paid or free Apple Developer account, signed into Xcode (Xcode → Settings → Accounts).
2. The iPhone connected via USB (or on the same network for wireless debugging), trusted, and
   registered as a development device for your team at https://developer.apple.com/account
   (Certificates, Identifiers & Profiles → Devices).
3. `appium driver install xcuitest` run once locally (verify with `appium driver list --installed`).

## Finding the values for config.properties

- **`ios.udid`**: run `xcrun xctrace list devices` (or `idevice_id -l` if libimobiledevice is
  installed) with the iPhone connected — copy the device identifier shown in parentheses.
- **`ios.xcodeOrgId`**: your Apple Developer Team ID, found at
  https://developer.apple.com/account → Membership details, or in Xcode → Settings → Accounts →
  select your team → "Team ID".
- **`ios.xcodeSigningId`**: the signing identity string, typically `iPhone Developer` or
  `Apple Development` — visible in Keychain Access under "My Certificates", or via
  `security find-identity -v -p codesigning`.
- **`ios.updatedWDABundleId`**: a unique bundle identifier for the WebDriverAgent runner app
  (e.g. `com.yourteam.WebDriverAgentRunner`) that you've registered under your team in the
  Identifiers section of the Apple Developer portal — Appium needs this to install/re-sign
  WebDriverAgent on the device under your provisioning profile.

## First run on a new device

The very first Appium session against a fresh real device typically takes longer (a few minutes)
because Xcode has to build and sign WebDriverAgent and install it on the device. If it fails with
a codesigning or provisioning-profile error, open
`~/.appium/node_modules/appium-xcuitest-driver/WebDriverAgent` (or wherever the driver installed
it) in Xcode once, select your team under the `WebDriverAgentRunner` target's Signing &
Capabilities tab, and build/run it manually against the device once to let Xcode resolve the
provisioning profile — subsequent Appium-driven runs will then reuse it.

## Not covered here

Automating certificate/profile provisioning (e.g. via `fastlane match` or CI secrets) is out of
scope for this framework — that's an organization-level Apple Developer account decision, not a
test-framework concern.
