## Running your tests
- Do remember to switch the USERNAME and ACCESS_KEY with your own browserstack credentials.
- Upload your Native App (.ipa file) to BrowserStack servers using upload API:

  ```
  curl -u "username:accesskey" -X POST "https://api.browserstack.com/app-automate/upload" -F "file=@/path/to/app/file/Application-debug.ipa"
  ```

- If you do not have an .ipa file and looking to simply try App Automate, [you can download our sample app and upload](https://www.browserstack.com/app-automate/sample-apps/ios/WordPressSample.ipa)
to the BrowserStack servers using the above API.
- Update the desired capability "app" with the App URL returned from the above API call

For frameworks integration with BrowserStack, refer to their individual repositories -

- [JUnit](https://github.com/browserstack/junit-appium-app-browserstack)
- [TestNG](https://github.com/browserstack/testng-appium-app-browserstack)