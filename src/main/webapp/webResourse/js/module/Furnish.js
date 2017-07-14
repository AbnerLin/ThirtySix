/**
 * FurnishClass class.
 * 
 * @param imagePath
 * @param classID
 * @param isEnable
 * @returns
 */
function _FurnishClass(imagePath, classID, isVisible, isNameable) {
	this.imagePath = imagePath;
	this.classID = classID;
	this.isVisible = isVisible;
	this.isNameable = isNameable;
}

/**
 * FurnishClass module.
 */
var FurnishClass = (function() {
	var self = {};

	var data = new DataKeeper();
	var dataUrl = "map/getFurnishClass";

	self.init = function() {
		return $.ajax({
			url : App.URL + dataUrl,
			async : true,
			success : function(response, status, jqXHR) {
				$.each(response.data, function(key, value) {
					var _data = new _FurnishClass( //
					Images.URL + value.detail.imagePath, //
					value.detail.classID, //
					value.detail.isVisible, //
					value.detail.isNameable //
					);

					self.data.add(value.detail.name, _data);
				});
			}
		});
	}

	self.data = data;

	return self;
})();

/**
 * Furnish class.
 * 
 * @returns
 */
function _Furnish(id, alias, x, y) {
	this.id = id;
	this.alias = alias;
	this.x = x;
	this.y = y;
}

/**
 * Furnish module.
 */
var Funish = (function() {
	var self = {};

	var data = new DataKeeper();
	var dataUrl = "";

	self.init = function() {
		return $.ajax({
		// TODO
		});
	}

	self.data = data;

	return self;
})();