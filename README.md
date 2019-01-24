# Cordova Client Certificate Plugin

Plugin that uses a client certificate for authentication.

## Usage

### Installing the plugin

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello

Install the plugin, for example:

    $ cd hello
    $ cordova plugin add https://github.com/mwaylabs/cordova-plugin-client-certificate

### Sample program

**WARNING:** Client certificate should NEVER be included in the `www` folder or any other part of a published app.

Copy a client certificate into the `www` folder.

Edit `www/js/index.js` and add the following code inside `onDeviceReady`:

```js
    var success = function(message) {
        alert(message);
    }

    var failure = function(error) {
        alert("Error:" + error);
    }

    clientCertificate.registerAuthenticationCertificate("certfilePath/cert.p12", "s3cr37", success, failure);
```

### Build and run

Install iOS platform

    cordova platform add ios

Run the code

    cordova run

## More Info

For more information on setting up Cordova see [the Cordova CLI documentation](https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli)

For more info on plugins see the [Cordova Plugin Development Guide](https://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)
