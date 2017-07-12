String.prototype.format = function() {
	var str = this;
	for (var i = 0; i < arguments.length; i++) {
		var reg = new RegExp("\\{" + i + "\\}", "gm");
		str = str.replace(reg, arguments[i]);
	}
	return str;
}

var Images = {};
Images.URL = (function() {
	return App.URL + "images/";
})();
