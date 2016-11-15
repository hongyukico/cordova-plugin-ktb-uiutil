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