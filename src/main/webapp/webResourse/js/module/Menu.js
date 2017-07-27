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
function _Menu(id, name, style) {
	this.id = id;
	this.name = name;
	this.style = style;
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
					var menu = new _Menu(value.classID, value.className,
							value.style);
					
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
	
	self.getItemByClassId = function(classId) {
		return menuData.get(classId);
	};

	self.getAll = function() {
		return menuData.getAll();
	};
	
	return self;
})();