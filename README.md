# cordova-client-cert-authentication
This plugin adds certificate-based authentication (SSO) to your cordova application. There is to extra coding or android  knowledge required when using this plugin. It does not contain any JavaScript part since it just waits until the SSLSocket asks the client for a certificate and then shows the default client-cert pop-up you'd also get when visiting your web page using the android chrome browser.

### Usage
Follow the official [Get Started](https://cordova.apache.org/#getstarted) guide of cordova or use your existing application.

Add the following plugin to your `config.xml`
```xml
<plugin name="cordova-client-cert-authentication" version="1" />
```

Then run your usual commands:
```bash
cordova platform add android
cordova build
...
```
