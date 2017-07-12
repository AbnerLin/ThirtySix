/**
 * FurnishClass.
 */
var FurnishClass = (function() {
	var data = undefined;
	var dataUrl = "getFurnishClass";

	function init() {
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
					addFurnishClass(key, _data);
				});
			}
		});
	}

	function addFurnishClass(key, obj) {
		data[key] = obj;
	}

	function getFurnishClass(key) {
		return data[key];
	}

	function getAll() {
		return data;
	}

	return {
		init : init,
		addFurnishClass : addFurnishClass,
		getFurnishClass : getFurnishClass,
		getAll : getAll
	}
})();
