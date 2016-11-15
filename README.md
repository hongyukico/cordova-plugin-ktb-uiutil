# Cordova request plugin

for iOS and Android, by [ktb](https://github.com/hongyukico)

## Description
网页进行数据请求时，通过原生请求。返回给网页成功、失败的回调并返回相对应的数据，支持android、ios平台


* 1.0.0 Works with Cordova 3.x
* 1.0.1+ Works with Cordova >= 4.0

## Installation

```
cordova plugin add cordova-plugin-ktb-request
```


## Usage

```javascript
	var exec = require('cordova/exec');

	exports.getRequest = function(arg0, success, error) {
		exec(success, error, "RequestPlugin", "getRequest", [arg0]);
	};

	exports.postRequest = function(arg0, success, error) {
		exec(success, error, "RequestPlugin", "postRequest", [arg0]);
	};

	exports.putRequest = function(arg0, success, error) {
		exec(success, error, "RequestPlugin", "putRequest", [arg0]);
	};

	exports.deleteRequest = function(arg0, success, error) {
		exec(success, error, "RequestPlugin", "deleteRequest", [arg0]);
	};
```
