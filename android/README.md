## Running your tests
- Do remember to switch the USERNAME and ACCESS_KEY with your own browserstack credentials.
- Upload your Native App (.apk file) to BrowserStack servers using upload API:

  ```
  curl -u "username:accesskey" -X POST "https://api.browserstack.com/app-automate/upload" -F "file=@/path/to/app/file/Application-debug.apk"
  ```

- If you do not have an .apk file and looking to simply try App Automate, [you can download our sample app and upload](https://www.browserstack.com/app-automate/sample-apps/android/WikipediaSample.apk)
to the BrowserStack servers using the above API.
- Update the desired capability "app" with the App URL returned from the above API call

For running LocalSample tests, you can download `browserstack-local-java.jar` from [here](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22browserstack-local-java%22).

For frameworks integration with BrowserStack, refer to their individual repositories -

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [TestNG](https://github.com/browserstack/testng-appium-app-browserstack)
