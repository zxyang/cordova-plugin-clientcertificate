# Cordova Client Certificate Plugin
This plugin uses iOS implementation and API from mwaylabs/cordova-plugin-client-certificate

This plugin is a forked from zxyang/cordova-plugin-clientcertificate

This plugin handles client certificate request from both iOS and Android WebView the full path to the certificat is required.

It is possible too add dynamically certificats to a apps in r/w folder.

#Todo
Add Windows platform

#Use Steps
Clone the plugin

    $ git clone https://github.com/agenceaddictic/cordova-plugin-client-certificate-addictic.git

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello
    
Install the plugin

    $ cd hello
    $ cordova plugin add ../cordova-plugin-client-certificate-addictic
    

Copy a client certificate (PKCS12 format) to your www/ folder.

Add the following code inside `onDeviceReady`

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
	clientCertificate.register(p12path, p12pass, certificateRegistred, onFailure);
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

Install iOS and Android platform

    cordova platform add ios
    cordova platform add android
    
Run the code

    cordova run android
    cordova run ios

