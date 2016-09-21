# Cordova Client Certificate Plugin
This plugin uses iOS implementation and API from mwaylabs/cordova-plugin-client-certificate

This plugin is a forked from zxyang/cordova-plugin-clientcertificate

This plugin handles client certificate request from both iOS and Android WebView the full path to the certificat is required.

This plugin read the certificate in any folder even in a cordova.file.dataDirectory (r/w folder). (So you can retrieve you cert file from a API and use it)

#Todo
Add Windows platform

#Prerequisites
Add to config.xml the following
```xml
<platform name="android">
	<preference name="AndroidPersistentFileLocation" value="Internal" />
</platform>
<platform name="ios">
	<preference name="iosPersistentFileLocation" value="Library" />
</platform>
```

#Use Steps
Clone the plugin

    $ git clone https://github.com/agenceaddictic/cordova-plugin-client-certificate-addictic.git

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello
    
Install the plugin

    $ cd hello
    $ cordova plugin add ../cordova-plugin-client-certificate-addictic
    
Install plugin File

    $ cordova plugin add cordova-plugin-file

    

Exemple :
Copy of an embedded certificate in /www (read only) to a directory accessible in read/write
Retrieve the final path and execute the handshake SSL

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

