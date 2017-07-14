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

	var data = {};
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

					self.addFurnishClass(value.detail.name, _data);
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
	var data = {};
	var dataUrl = "";

	self.init = function() {
		return $.ajax({
		// TODO
		});
	}

	self.addFurnish = function(key, obj) {
		data[key] = obj;
	}

	self.getFurnish = function(key) {
		return data[key];
	}

	self.getAll = function() {
		return data;
	}

	return self;
})();