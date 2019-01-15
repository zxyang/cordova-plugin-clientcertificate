# cordova-client-cert-authentication
This plugin adds certificate-based authentication (SSO) to your cordova application. There is no extra coding or android  knowledge required when using this plugin. It does not contain any JavaScript part since it just waits until the SSLSocket asks the client for a certificate and then shows the default client-cert pop-up you would also get when visiting your web page using the android chrome browser.

## Usage
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

## Why is there no support for iOS?
That's not easy to answer - the main point is, that iOS handles client certificates in a different way than android. [This comment](https://github.com/johannes-staehlin/cordova-client-cert-authentication/issues/5#issuecomment-424494990) provides a good summary:

> On Android the user can download and install a standard certificate on the device, then use the standard selection mechanism to select the certificate needed to access a server. On iOS this is not really possible.
> 
> On iOS, the application has to open the certificate file and configure or store it somehow. But I think the certificate file cannot have the standard p12 extension since iOS has its own handler for that extension. And it may be necessary for the iOS app to handle user password very carefully, meaning never store user password itself.

This means, Android applications can "ask for access" to the main KeyStore of the OS, while on iOS the certificate has to be  "imported into keychain of the app" in any way, thus a custom implementation is needed (please **never** ship the client certificate with the app in the assert folder).
Therefore, I am not sure if a common cordova plugin even makes sense - but if someone comes up with a good PR, I am open to merge it.
See [#5](https://github.com/johannes-staehlin/cordova-client-cert-authentication/issues/5) for the full discussion. 


## Contribution
Feel free to contribute code to this project through GitHub by forking the repository and sending a pull request.


## License
    
    Copyright 2018 Johannes Stählin
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
