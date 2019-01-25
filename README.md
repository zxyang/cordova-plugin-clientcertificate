# Cordova Client Certificate Plugin

Plugin that uses a client certificate for authentication.

This plugin uses iOS implementation and API from mwaylabs/cordova-plugin-client-certificate

This plugin is forked from zxyang/cordova-plugin-clientcertificate / addictic/cordova-plugin-client-certificate-addictic

This plugin handles client certificate request from both iOS and Android WebView; the full path to the certificate is required.

This plugin reads the certificate in any folder even in a cordova.file.dataDirectory (r/w folder). (So you can retrieve a cert file from an API and use it)

TBD NOTE THAT ANDROID PLATFORM IMPLEMENTATION IS NOT TESTED WITH JAVASCRIPT UPDATES from mwaylabs/cordova-plugin-client-certificate

# FUTURE TODO

- TEST ANDROID PLATFORM IMPLEMENTATION WITH JAVASCRIPT UPDATES from mwaylabs/cordova-plugin-client-certificate
- Add Windows platform

## Usage

## Prerequisites

Add the following to `config.xml`:

```xml
<platform name="android">
	<preference name="AndroidPersistentFileLocation" value="Internal" />
</platform>
<platform name="ios">
	<preference name="iosPersistentFileLocation" value="Library" />
</platform>
```

Plugin that uses a client certificate for authentication.

## Usage

### Installing the plugin

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello

Install the plugin, for example:

    $ cd hello
    $ cordova plugin add https://github.com/mwaylabs/cordova-plugin-client-certificate

Install plugin File

    $ cordova plugin add cordova-plugin-file

### Sample program

**WARNING:** Client certificate should NEVER be included in the `www` folder or any other part of a published app.

ALTERNATIVE 1:

Copy a client certificate into the `www` folder.

Edit `www/js/index.js` and add the following code inside `onDeviceReady`:

```js
    clientCertificate.registerAuthenticationCertificate("certfilePath/cert.p12", "s3cr37", success, failure);
```

ALTERNATIVE 2:

Copy of an embedded certificate in /www (read only) to a directory accessible in read/write
Retrieve the final path and execute the handshake SSL

Add the following code inside `onDeviceReady`:

```js
var certAutomate = 'mycert.p12';
var certFolder = 'certificates/';
var appDir = cordova.file.applicationDirectory + 'www/';
var datDir = cordova.file.dataDirectory;

window.resolveLocalFileSystemURL(datDir, function(DirectoryEntry){
	// Create certFolder if doesn't exists in a r/w location (dataDirectory)
	DirectoryEntry.getDirectory(certFolder, {create: true, exclusive: false}, copyCertificateAutomate, onFailure);
}, onFailure);

var copyCertificateAutomate = function(DirectoryEntry){
	window.resolveLocalFileSystemURL(appDir + certFolder + certAutomate, function(FileEntry){
		window.resolveLocalFileSystemURL(datDir + certFolder + certAutomate, certAuthenticate, function(){
			// Copy the file to the r/w folder if not exists
			FileEntry.copyTo(DirectoryEntry, certAutomate, onSuccess, onFailure);
		});
	}, certAuthenticate);
};

var certAuthenticate = function() {
	// Full path to the cert
	var p12path = datDir.substring(7) + certFolder + certAutomate;
	var p12pass = 'myPassword';
	clientCertificate.registerAuthenticationCertificate(p12path, p12pass, certificateRegistred, onFailure); // UPDATED API
};

var certificateRegistred = function(message) {
	console.log(message);
	// launch your web service that requires certificate authentication here
};

var onSuccess = function(message){
	console.log('Success : ', message);
};

var onFailure = function(message){
	console.log('Error : ', message);
};
```

### Build and run

Install iOS and Android platform

    cordova platform add ios
    cordova platform add android

Run the code

    cordova run android
    cordova run ios

## More Info

For more information on setting up Cordova see [the Cordova CLI documentation](https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli)

For more info on plugins see the [Cordova Plugin Development Guide](https://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)
