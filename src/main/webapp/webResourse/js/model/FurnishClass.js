/**
 * FurnishClass.
 */
var FurnishClass = (function() {
	var self = {};

	var data = undefined;
	var dataUrl = "getFurnishClass";

	self.init = function() {
		return $.ajax({
			url : App.URL + dataUrl,
			async : true,
			success : function(response, status, jqXHR) {
				data = {};

				$.each(response.data, function(key, value) {
					var _data = {
						imagePath : Images.URL + value.detail.imagePath,
						classID : value.detail.classID,
						enable : value.enable
					}
					self.addFurnishClass(key, _data);
				});
			}
		});
	}

	self.addFurnishClass = function(key, obj) {
		data[key] = obj;
	}

	self.getFurnishClass = function(key) {
		return data[key];
	}

	self.getAll = function() {
		return data;
	}

	return self;
})();
