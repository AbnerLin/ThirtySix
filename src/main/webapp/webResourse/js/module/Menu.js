/**
 * Item class.
 * 
 * @param id
 * @param name
 * @param imagePath
 * @param price
 * @param description
 * @param isDisplay
 * @returns
 */
function _Item(id, name, imagePath, price, description, isDisplay) {
	this.id = id;
	this.name = name;
	this.imagePath = imagePath;
	this.price = price;
	this.description = description;
	this.isDisplay = isDisplay;
}

/**
 * Menu class.
 * 
 * @param id
 * @param name
 * @param style
 * @returns
 */
function _ItemClass(id, name, imagePath, description, mealType) {
	this.id = id;
	this.name = name;
	this.imagePath = imagePath;
	this.description = description;
	this.mealType = mealType;
	/** <itemId, _Item> */
	this.itemMap = new DataKeeper();
}

/**
 * Menu module.
 */
var Menu = (function() {
	var self = {};
	var dataUrl = App.URL + "menu/getMenu";

	/** <itemClassId, itemClass> */
	var menuData = new DataKeeper();

	self.init = function() {
		return App.ajax({
			url : dataUrl,
			success : function(data, textStatus, jqXHR) {
				var dataObj = data.data;
				$.each(dataObj, function(key, value) {
					var menu = new _ItemClass( //
					value.classID, //
					value.className, //
					Images.URL + value.imagePath, //
					value.description, //
					value.mealType //
					);

					/** Compose item list. */
					$.each(value.itemList, function(key, itemValue) {
						var item = new _Item( //
						itemValue.itemID, //
						itemValue.name, //
						Images.URL + itemValue.imagePath, //
						itemValue.price, //
						itemValue.description, //
						itemValue.isDisplay //
						);
						menu.itemMap.add(item.id, item);
					});

					menuData.add(menu.id, menu);
				});
			}
		});
	};
	
	self.getItemByItemId = function(itemId) {
		var result = null;
		
		$.each(menuData.getAll(), function(key, value) {
			var flag = true;
			$.each(value.itemMap.data, function(innerKey, innerValue) {
				if(innerValue.id == itemId) {
					result = innerValue;
					return flag = false;
				}
			});
			return flag;
		});
		
		return result;
	};

	self.getItemByClassId = function(classId) {
		return menuData.get(classId);
	};

	self.getAll = function() {
		return menuData.getAll();
	};

	return self;
})();