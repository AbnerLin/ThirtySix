/**
 * Data Keeper class.
 * 
 * @returns
 */
function DataKeeper() {
	this.data = {};
}

DataKeeper.prototype.add = function(key, obj) {
	this.data[key] = obj;
}

DataKeeper.prototype.get = function(key) {
	return this.data[key];
}

DataKeeper.prototype.remove = function(key) {
	delete this.data[key];
}

DataKeeper.prototype.getAll = function() {
	return this.data;
}

DataKeeper.prototype.reset = function() {
	this.data = {};
}