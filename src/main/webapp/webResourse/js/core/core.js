var App = {};

App.URL = (function() {
	return "/thirtySix/";
})();

App.LoadScript = function(script) {
	$.ajax({
		async : false,
		dataType : "script",
		url : script,
		success : function() {
			console.log("{0} loaded !".format(script));
		}
	});
};

App.alertSuccess = function(msg) {
	alertify.success(msg);
};

App.alertError = function(msg) {
	alertify.error(msg);
};

$(document).ready(function() {
	App.LoadScript("js/core/common.js");
	App.LoadScript("js/model/Auth.js");
	App.LoadScript("js/model/FurnishClass.js");

});
