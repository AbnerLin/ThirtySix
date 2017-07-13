$(document).ready(function() {
	App.LoadScript("js/core/common.js");
	App.LoadScript("js/model/Auth.js");
	App.LoadScript("js/model/FurnishClass.js");
});

var App = (function() {
	self = {};

	var rootURL = "/thirtySix/";

	self.URL = (function() {
		return rootURL;
	})();

	self.LoadScript = function(script) {
		$.ajax({
			async : false,
			dataType : "script",
			url : script,
			success : function() {
				console.log("{0} loaded !".format(script));
			}
		});
	};

	self.alertSuccess = function(msg) {
		alertify.success(msg);
	};

	self.alertError = function(msg) {
		alertify.error(msg);
	};

	return self;
})();
