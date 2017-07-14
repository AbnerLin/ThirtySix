String.prototype.format = function() {
	var str = this;
	for (var i = 0; i < arguments.length; i++) {
		var reg = new RegExp("\\{" + i + "\\}", "gm");
		str = str.replace(reg, arguments[i]);
	}
	return str;
}

var Images = (function() {
	var self = {};
	var imagesUrl = App.URL + "images/";

	self.URL = imagesUrl;

	return self;
})();

/**
 * uuid
 */
(function($) {
	$.uuid = function() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
				function(c) {
					var r = Math.random() * 16 | 0, v = c == 'x' ? r
							: (r & 0x3 | 0x8);
					return v.toString(16);
				});
	};
})(jQuery);