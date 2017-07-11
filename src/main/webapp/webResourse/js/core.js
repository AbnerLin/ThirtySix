var App = {};
var Images = {};
var Customer = {};

App.URL = (function() {
	return "/thirtySix/";
})();

/**
 * Images.
 */
Images = (function() {
	var instance = null;

	/** init */
	function createInstance() {
		this.data = {};

		return {
			addImage : function(key, obj) {
				data[key] = obj;
			},
			getImage : function(key) {
				return data[key];
			},
			getAll : function() {
				return data;
			}
		}
	}

	/** singleton */
	return {
		getInstance : function() {
			if (!instance) {
				instance = createInstance();
			}
			return instance;
		}
	}
})();

Images.URL = (function() {
	return App.URL + "images/";
})();

Customer = (function() {
	var instance = null;

	/** init */
	function createInstance() {
		this.data = {};

		return {
		// public method
		}
	}

	return {
		getInstance : function() {
			if (!instance) {
				instance = createInstance();
			}
			return instance;
		}
	}
})();
