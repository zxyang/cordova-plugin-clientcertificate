# cordova-client-cert-authentication for Android

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

## Contributing

Feel free to contribute code to this project through GitHub by forking the repository and sending a pull request.

## License

    Copyright 2019 Christopher J. Brody

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
