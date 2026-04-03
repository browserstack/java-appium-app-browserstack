# java-appium-app-browserstack (BrowserStack SDK)

This repository demonstrates how to run Appium Java tests on [BrowserStack App Automate](https://app-automate.browserstack.com) using the **BrowserStack SDK**. All device, credential, and build configuration is managed via `browserstack.yml` — no hardcoded capabilities in test code.

## Setup

### Requirements

1. **Java 8+**
   - For Windows, download the latest version from [here](https://java.com/en/download/) and run the installer.
   - For Mac and Linux, run `java -version` to check your version. Download a different version from [here](https://java.com/en/download/) if needed.

2. **Maven**
   - Download Maven from [here](https://maven.apache.org/download.cgi).
   - Follow the installation instructions [here](https://maven.apache.org/install.html).

### Install the dependencies

Navigate to the `java_8` directory and run:

```sh
cd java_8/
mvn clean install
```

---

## Getting Started

### Step 1 — Upload your App to BrowserStack

Upload your Android (`.apk` / `.aab`) or iOS (`.ipa`) app to BrowserStack using the REST API:

```sh
curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
  -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
  -F "file=@/path/to/your/app"
```

Note the `app_url` value (e.g. `bs://xxxxxxx`) returned in the response — you'll need it in the next step.

> **Don't have an app?** Use the BrowserStack sample apps:
> - [Sample Android app](https://www.browserstack.com/app-automate/sample-apps/android/WikipediaSample.apk)
> - [Sample iOS app](https://www.browserstack.com/app-automate/sample-apps/ios/BStackSampleApp.ipa)

---

### Step 2 — Configure `browserstack.yml`

Open `java_8/browserstack.yml` and set your credentials, app, and target devices.

```yaml
# =====================
# BrowserStack Credentials
# =====================
# Either set values directly here, or export as environment variables:
#   export BROWSERSTACK_USERNAME=your_username
#   export BROWSERSTACK_ACCESS_KEY=your_access_key
userName: YOUR_USERNAME
accessKey: YOUR_ACCESS_KEY

# =====================
# Reporting
# =====================
projectName: First Java Project
buildName: browserstack-build-1
buildIdentifier: '#${BUILD_NUMBER}'

# =====================
# App under test
# =====================
app: bs://<app-id>   # Replace with the app_url from Step 1

# =====================
# Target devices
# =====================
platforms:
  - deviceName: <device-name>
    platformVersion: <os-version>
    platformName: <android/ios>

parallelsPerPlatform: 1

# =====================
# Local Testing (optional)
# =====================
browserstackLocal: <true/false>  # Set to true for local/internal environment tests
```

---

### Step 3 — Run your tests

Make sure you are inside the `java_8/` directory before running any of the following commands.

#### Run Android sample test

```sh
mvn test -P android-first-test
```

#### Run iOS sample test

```sh
mvn test -P ios-first-test
```

#### Run Android local test

```sh
mvn test -P android-local-test
```

#### Run iOS local test

```sh
mvn test -P ios-local-test
```

---


## Local Testing

Local Testing lets you test apps that access resources on your internal or staging environments.

To enable it, set the following in `browserstack.yml`:

```yaml
browserstackLocal: true
```

The SDK will automatically start and stop the BrowserStack Local tunnel — no manual binary setup needed. Then run the local test profile:

```sh
# Android
mvn test -P android-local-test

# iOS
mvn test -P ios-local-test
```

> **Note for macOS users:** If you encounter Apple permission issues with the Local binary, go to:
> `System Preferences → Security & Privacy → General → Allow app`

---

## Notes on java-client 8.x

This module uses `io.appium:java-client:8.x`. Key differences from 7.x:

- Use `AppiumBy` instead of the deprecated `MobileBy`.
- `MobileElement`, `IOSElement`, `AndroidElement` are removed — use `WebElement` instead.
- `WebDriverWait` requires a `Duration` argument:
  ```java
  // java-client 7.x
  new WebDriverWait(driver, 30)

  // java-client 8.x
  new WebDriverWait(driver, Duration.ofSeconds(30))
  ```

See the full [v7 to v8 migration guide](https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#mobileelement) for details.

---

## View Test Results

After running your tests, visit the [App Automate Dashboard](https://app-automate.browserstack.com/dashboard) to see:
- Test status (pass/fail)
- Video recordings
- Device logs
- Network logs

---

## Getting Help

If you run into any issues, check the [BrowserStack Support page](https://www.browserstack.com/support/app-automate) or [contact us](https://www.browserstack.com/contact#technical-support).