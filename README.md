# Cordova uiutil plugin

for iOS and Android, by [ktb](https://github.com/hongyukico)

## Description
基本的原生UI控件。加载框、对话框、提示框，支持android、ios平台


* 1.0.0 Works with Cordova 3.x
* 1.0.1+ Works with Cordova >= 4.0

## Installation

```
cordova plugin add cordova-plugin-ktb-uiutil
```


## Usage

```javascript
	var exec = require('cordova/exec');

	exports.openAlert = function(arg0, success, error) {
		exec(success, error, "UIUtil", "openAlert", [arg0]);
	};

	exports.openConfirmAlert = function(arg0, success, error) {
		exec(success, error, "UIUtil", "openConfirmAlert", [arg0]);
	};

	exports.openLoading = function(arg0, success, error) {
		exec(success, error, "UIUtil", "openLoading", [arg0]);
	};
```
