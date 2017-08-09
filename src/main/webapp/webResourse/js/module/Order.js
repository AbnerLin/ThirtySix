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
	var orderUrl = App.URL + "order/sendOrder";
	
	self.sendOrder = function(dataObject, btn) {
		return App.ajax({
			url : orderUrl,
			disableButton : btn,
			contentType : "application/json",
			data: JSON.stringify(dataObject),
			success : function(data, textStatux, jqXHR) {
				if(data.status)
					App.alertSuccess("送單成功！");
				else
					App.alertError(data.message);
			}
		});
	};
	
	return self;
})();