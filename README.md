# java-appium-app-browserstack

This repository demonstrates how to run Appium Java tests on BrowserStack App Automate.

## Setup

### Requirements

1. Java 8+

- If Java is not installed, follow these instructions:
    - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
    - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Maven

    - If Maven is not downloaded, download it from [here](https://maven.apache.org/download.cgi)
    - For installation, follow the instructions [here](https://maven.apache.org/install.html)

### Install the dependencies

To install the dependencies, run the following command in the project's base folder

```cmd
mvn clean install
```

## Getting Started

Getting Started with Appium tests in Java on BrowserStack couldn't be easier!

### Run your first test :

**1. Upload your Android or iOS App**

Upload your Android app (.apk or .aab file) or iOS app (.ipa file) to BrowserStack servers using our REST API. Here is an example cURL request :

```
curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/apk/file"
```

Ensure that @ symbol is prepended to the file path in the above request. Please note the `app_url` value returned in the API response. We will use this to set the application under test while configuring the test later on.

**Note**: If you do not have an .apk or .ipa file and are looking to simply try App Automate, you can download and test using our [sample Android app](https://www.browserstack.com/app-automate/sample-apps/android/WikipediaSample.apk) or [sample iOS app](https://www.browserstack.com/app-automate/sample-apps/ios/BStackSampleApp.ipa).

**2. Configure and run your first test**

Open `BrowserStackSample.java` file in the `android` directory or `ios` directory :

- Replace `YOUR_USERNAME` & `YOUR_ACCESS_KEY` with your BrowserStack access credentials. Get your BrowserStack access credentials from [here](https://www.browserstack.com/accounts/settings)

- Replace `bs://<app-id>` with the URL obtained from app upload step

- Set the device and OS version

- If you have uploaded your own app update the test case

- To run the test, use the following command in the base directory :

    - For Android test, run

    ```cmd
    mvn test -P android-first-test
    ```

    - For iOS test, run

    ```cmd
    mvn test -P ios-first-test
    ```

- You can access the test execution results, and debugging information such as video recording, network logs on [App Automate dashboard](https://app-automate.browserstack.com/dashboard)

---

### Use Local testing for apps that access resources hosted in development or testing environments :

**1. Upoad your Android or iOS App**

Upload your Android app (.apk or .aab file) or iOS app (.ipa file) that access resources hosted on your internal or test environments to BrowserStack servers using our REST API. Here is an example cURL request :

```
curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/apk/file"
```

Ensure that @ symbol is prepended to the file path in the above request. Please note the `app_url` value returned in the API response. We will use this to set the application under test while configuring the test later on.

**Note**: If you do not have an .apk or .ipa file and are looking to simply try App Automate, you can download and test using our [sample Android Local app](https://www.browserstack.com/app-automate/sample-apps/android/LocalSample.apk) or [sample iOS Local app](https://www.browserstack.com/app-automate/sample-apps/ios/LocalSample.ipa).

**2. Configure and run your local test**

Open `BrowserStackSampleLocal.java` file in the `android` or `ios` directory :

- Replace `YOUR_USERNAME` & `YOUR_ACCESS_KEY` with your BrowserStack access credentials. Get your BrowserStack access credentials from [here](https://www.browserstack.com/accounts/settings)

- Replace `bs://<app-id>` with the URL obtained from app upload step

- Set the device and OS version

- Ensure that `browserstack.local` capability is set to `true`. Within the test script, there is code snippet that automatically establishes Local Testing connection to BrowserStack servers using Java binding for BrowserStack Local.

- If you have uploaded your own app update the test case

- To run the test, use the following command in the base directory :

    - For Android test, run

    ```cmd
    mvn test -P android-local-test
    ```

    - For iOS test, run

    ```cmd
    mvn test -P ios-local-test
    ```

- You can access the test execution results, and debugging information such as video recording, network logs on [App Automate dashboard](https://app-automate.browserstack.com/dashboard)


## Integration with other Java frameworks

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [TestNg](https://github.com/browserstack/testng-appium-app-browserstack)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).
