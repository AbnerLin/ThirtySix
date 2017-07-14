/**
 * Data Keeper class.
 * 
 * @returns
 */
function DataKeeper() {
	this.data = {};
}

DataKeeper.add = function(key, obj) {
	this.data[key] = obj;
}

DataKeeper.get = function(key) {
	return this.data[key];
}

DataKeeper.getAll = function() {
	return this.data;
}