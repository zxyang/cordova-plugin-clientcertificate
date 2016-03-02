# Cordova Client Certificate Plugin
This plugin uses iOS implmentation and API from mwaylabs/cordova-plugin-client-certificate

This plugin handles client certificate request from both iOS and Android WebView.

#Use Steps
Clone the plugin

    $ git clone https://github.com/zxyang/cordova-plugin-clientcertificate.git

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello
    
Install the plugin

    $ cd hello
    $ cordova plugin add ../cordova-plugin-clientcertificate
    

Copy a client certificate (PKCS12 format) to your www/ folder.

Add the following code inside `onDeviceReady`

```js
    var success = function(message) {
        alert(message);
    }

    var failure = function(error) {
        alert("Error:" + error);
    }

    clientCertificate.register("cert.p12", "password", success, failure);
```

Install iOS and Android platform

    cordova platform add ios
    cordova platform add android
    
Run the code

    cordova run android
    cordova run ios

