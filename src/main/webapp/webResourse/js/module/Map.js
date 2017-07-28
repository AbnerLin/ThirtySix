/**
 * FurnishClass class.
 * 
 * @param imagePath
 * @param classID
 * @param isEnable
 * @returns
 */
function _FurnishClass(enumName, imagePath, classID, isVisible, isNameable) {
	this.enumName = enumName;
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

	/** <className(ENUM), obj> */
	var data = new DataKeeper();
	var dataUrl = App.URL + "map/getFurnishClass";

	self.init = function() {
		return App.ajax({
			url :  dataUrl,
			success : function(response, status, jqXHR) {
				$.each(response.data, function(key, value) {
					var _data = new _FurnishClass( //
					value.detail.name, //
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

	self.getEnumNameById = function(classId) {
		var enumName = null;
		$.each(data.getAll(), function(key, value) {
			if (value.classID == classId) {
				enumName = key;
				return;
			}
		});
		return enumName;
	};

	return self;
})();

/**
 * Furnish class.
 * 
 * @param id
 * @param alias
 * @param x
 * @param y
 * @param _class (_FurnishClass.enumName)
 * @returns
 */
function _Furnish(id, alias, x, y, _class) {
	this.id = id;
	this.alias = alias;
	this.x = x;
	this.y = y;
	this._class = _class;
}

/**
 * Map class.
 * 
 * @param id
 * @param name
 * @param width
 * @param height
 * @returns
 */
function _Map(id, name, width, height) {
	this.id = id;
	this.name = name;
	this.width = width;
	this.height = height;
	/** <furnish.id, _Furnish> */
	this.furnishList = new DataKeeper();
}

/**
 * Map module.
 */
var Map = (function() {
	var self = {};

	/** <map.uuid, _Map> */
	var mapData = new DataKeeper();
	var dataUrl = App.URL + "map/getSeatMap";
	var dataSaveUrl = App.URL + "map/saveSeatMap";

	var saveBuffer = {};
	var removeBuffer = {};

	self.init = function() {
		return App.ajax({
			url : dataUrl,
			success : function(data, textStatus, jqXHR) {
				var mapDataArray = data.data;
				$.each(mapDataArray, function(key, value) {
					var map = new _Map(value.mapID, value.name, value.width, value.height);
					/** Compose furnish list */
					$.each(value.furnishList, function(key, innerValue) {
						
						var furnish = new _Furnish( //
								innerValue.furnishID, //
								innerValue.name, //
								innerValue.x, //
								innerValue.y, // 
								innerValue.furnishClass.name //
							); //
						map.furnishList.add(furnish.id, furnish);
					});
					
					/** Save to module. */
					self.saveOrUpdateMap(map);
				});
			}
		});
	};

	self.save = function() {
		var dataList = [];
		$.each(mapData.getAll(), function(key, value) {
			if (value) {
				/** new furnish */
				var furnishList = [];
				$.each(saveBuffer[key], function(key, value) {
					furnishList
							.push({
								furnishID : value.id,
								x : value.x,
								y : value.y,
								name : value.alias,
								furnishClassID : FurnishClass.data
										.get(value._class).classID
							});
				});

				/** remove furnish */
				var rmFurnishList = [];
				$.each(removeBuffer[key], function(key, value) {
					rmFurnishList.push(value);
				});

				dataList.push({
					mapID : key,
					name : value.name,
					width : value.width,
					height : value.height,
					newFurnishList : furnishList,
					removeFurnishList : rmFurnishList
				});
			}
		});

		return App.ajax({
			url : dataSaveUrl,
			contentType : 'application/json',
			data : JSON.stringify(dataList),
			success : function() {
				saveBuffer = [];
				removeBuffer = [];
				App.alertSuccess("儲存成功。");
			}
		});
	};

	self.saveOrUpdateMap = function(_map) {
		var map = mapData.get(_map.id) || (function() {
			mapData.add(_map.id, _map);
			return mapData.get(_map.id);
		})();

		map.name = _map.name;
		map.width = _map.width;
		map.height = _map.height;

		App.publish("/map/update", [ map ]);
	};

	self.updateFurnish = function(map, furnish) {
		var map = mapData.get(map.id) || (function() {
			mapData.add(map.id, map);
			return mapData.get(map.id);
		})();
		
		map.furnishList.add(furnish.id, furnish);
		
		addToSaveBuffer(map, furnish);
	};
	
	self.addFurnish = function(map, furnish, isLocalOperator) {
		var furnishData = mapData.get(map.id) || (function() {
			mapData.add(map.id, map);
			return mapData.get(map.id);
		})();
		furnishData.furnishList.add(furnish.id, furnish);

		App.publish("/furnish/add", [ map.id, furnish ]);

		if (isLocalOperator) {
			addToSaveBuffer(map, furnish);
		}
	};

	self.removeFurnish = function(mapId, furnishId, isLocalOperator) {
		mapData.get(mapId).furnishList.remove(furnishId);

		App.publish("/furnish/remove", [ mapId, furnishId ]);

		if (isLocalOperator) {
			addToRemoveBuffer(mapId, furnishId);
		}
	};

	self.getAllFurnish = function(mapId) {
		if (mapData.get(mapId))
			return mapData.get(mapId).furnishList;
		return null;
	};

	self.getFurnish = function(mapId, funirshId) {
		return self.getAllFurnish(mapId).get(funirshId);
	};
	
	self.getMap = function(mapId) {
		return mapData.get(mapId);
	};
	
	self.getAll = function() {
		return mapData.getAll();
	};
	
	/**
	 * Save to delivery buffer.
	 * 
	 * @param map
	 * @param furnish
	 * @returns
	 */
	function addToSaveBuffer(map, furnish) {
		var _saveBuffer = saveBuffer[map.id] || (function() {
			return saveBuffer[map.id] = [];
		})();
		
		var isExist = false;
		$.each(_saveBuffer, function(key, value) {
			if(value.id == furnish.id) {
				_saveBuffer[key] = furnish;
				isExist = true;
				return false;
			}
		});
		
		if(!isExist)
			_saveBuffer.push(furnish);
	} 
	
	/**
	 * Save to delivery Buffer.
	 * 
	 * @param mapId
	 * @param furnishId
	 * @returns
	 */
	function addToRemoveBuffer(mapId, furnishId) {
		/** clear if saveBuffer exist */
		var _saveBuffer = saveBuffer[mapId] || (function() {
			return saveBuffer[mapId] = [];
		})();
		$.each(_saveBuffer, function(key, value) {
			if(furnishId == value.id) {
				_saveBuffer.splice(key, 1);
				return false;
			}
		});

		var _removeBuffer = removeBuffer[mapId] || (function() {
			return removeBuffer[mapId] = [];
		})();
		_removeBuffer.push(furnishId);
	}

	return self;
})();