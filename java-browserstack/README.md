# java-appium-app-browserstack

This repository demonstrates how to run Appium Java tests on BrowserStack App Automate.

## Setup

### Requirements

1. Java 8+

- If Java is not installed, follow these instructions:
    - For Windows, download latest java version from [here](https://java.com/en/download/) and run the installer executable
    - For Mac and Linux, run `java -version` to see what java version is pre-installed. If you want a different version download from [here](https://java.com/en/download/)

2. Eclipse IDE or IntelliJ IDEA

    - If not installed, download and install Eclipse IDE from [here](https://www.eclipse.org/downloads/) or IntelliJ IDEA from [here](https://www.jetbrains.com/idea/download/#section=windows)

### Install the dependencies

1. Import the project as an existing Maven project in your IDE

2. Clean and build the imported project

## Getting Started

Getting Started with Appium tests in Java on BrowserStack couldn't be easier!

### Upoad your Android or iOS App

Upload your Android app (.apk or .aab file) or iOS app (.ipa file) to BrowserStack servers using our REST API. Here is an example cURL request :

```
curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/apk/file"
```

Ensure that @ symbol is prepended to the file path in the above request. Please note the `app_url` value returned in the API response. We will use this to set the application under test while configuring the test later on.

**Note**: If you do not have an .apk or .ipa file and are looking to simply try App Automate, you can download and test using our [sample Android app](https://www.browserstack.com/app-automate/sample-apps/android/WikipediaSample.apk) or [sample iOS app](https://www.browserstack.com/app-automate/sample-apps/ios/BStackSampleApp.ipa).

### **Run first test :**

Open `BrowserStackAndroid.java` file for Android test or `BrowserStackiOS.java` for iOS test

- Replace `YOUR_USERNAME` & `YOUR_ACCESS_KEY` with your BrowserStack access credentials

- Replace `bs://<app-id>` wkth the URL obtained from app upload step

- Set the device and OS version

- If you have uploaded your own app update the test case

- Run `BrowserStackAndroid.java` for android test or `BrowserStackiOS.java` for iOS test

For more details, refer to our documentation - [Get Started with your first test on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java)

### **Use Local testing for apps that access resources hosted in development or testing environments :**

Refer to our documentation - [Get Started with Local testing on App Automate](https://www.browserstack.com/docs/app-automate/appium/getting-started/java/local-testing)

## Integration with other Java frameworks

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [TestNg](https://github.com/browserstack/testng-appium-app-browserstack)

## Getting Help

If you are running into any issues or have any queries, please check [Browserstack Support page](https://www.browserstack.com/support/app-automate) or [get in touch with us](https://www.browserstack.com/contact?ref=help).
