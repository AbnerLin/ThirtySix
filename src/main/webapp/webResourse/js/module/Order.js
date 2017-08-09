/**
 * Item of Order.
 * 
 * @param id
 * @param amount
 * @returns
 */
function _OrderItem(id, amount) {
	this.id = id;
	this.amount = amount;
}

/**
 * Order object.
 * 
 * @param customerId
 * @param List<_OrderItem>
 * @returns
 */
function _Order(customerId, orderList) {
	this.customerId = customerId;
	this.orderList = orderList;
}

/**
 * Order module.
 */
var Order = (function() {
	var self = {};
	
	self.sendOrder = function(dataObject) {
		console.log(dataObject);
	};
	
	return self;
})();