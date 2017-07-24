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
	var dataUrl = "map/getFurnishClass";

	self.init = function() {
		return $.ajax({
			url : App.URL + dataUrl,
			async : true,
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
			if(value.classID == classId) {
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
 * @param _class
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
 * Furnish module.
 */
var Map = (function() {
	var self = {};

	/** <map.uuid, _Map> */
	var mapData = new DataKeeper();
	var dataUrl = "map/getSeatMap";
	var dataSaveUrl = "map/saveSeatMap";

	var saveBuffer = {};
	var removeBuffer = {};

	self.init = function() {
		return $.ajax({
		// TODO
		});
	}

	self.save = function() {
		var dataList = [];
		$.each(mapData.getAll(), function(key, value) {
			if(value) {
				/** new furnish */
				var furnishList = [];
				$.each(saveBuffer[key], function(key, value){
					furnishList.push({
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

		return $.ajax({
			url : App.URL + dataSaveUrl,
			async : true,
			method : "POST",
			dataType : "json",
			contentType : 'application/json',
			data : JSON.stringify(dataList),
			success : function() {
				saveBuffer = [];
				removeBuffer = [];
			}
		});
	}

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
	
	self.addFurnish = function(map, furnish, isLocal) {
		var furnishData = mapData.get(map.id) || (function() {
			mapData.add(map.id, map);
			return mapData.get(map.id);
		})();
		furnishData.furnishList.add(furnish.id, furnish);

		App.publish("/furnish/add", [ map.id, furnish ]);

		if(isLocal) {
			/** buffer. */
			var _saveBuffer = saveBuffer[map.id] || (function() {
				return saveBuffer[map.id] = [];
			})();
			_saveBuffer.push(furnish);
		}
	};

	self.removeFurnish = function(mapId, furnishId, isLocal) {
		mapData.get(mapId).furnishList.remove(furnishId);

		App.publish("/furnish/remove", [ map.id, furnishId ]);
		
		if(isLocal) {
			/** buffer. */
			var _removeBuffer = removeBuffer[mapId] || (function() {
				return removeBuffer[mapId] = [];
			})();
			_removeBuffer.push(furnishId);
		}
	}

	self.getAllFurnish = function(mapId) {
		if(mapData.get(mapId))
			return mapData.get(mapId).furnishList;
		return null;
	}

	self.getFurnish = function(mapId, funirshId) {
		return self.getAllFurnish(mapId).get(funirshId);
	}

	return self;
})();