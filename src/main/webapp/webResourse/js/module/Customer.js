/**
 * Booking object
 * 
 * @param id
 * @param orderTime(orderTimeStringFormat)
 * @param volume
 * @param deliveryTime(deliveryTimeStringFormat)
 * @param isSend
 * @param itemId
 * @param customerId
 * @returns
 */
function _Booking(id, orderTime, volume, deliveryTime, isSend, itemId) {
	this.id = id;
	this.orderTime = orderTime;
	this.volume = volume;
	this.deliveryTime = deliveryTime;
	this.isSend = isSend;
	this.itemId = itemId;
}

/**
 * Customer objcect
 * 
 * @param id
 * @param name
 * @param phoneNumber
 * @param remark
 * @param furnish(Map._Furnish)
 * @param peopleCount
 * @param checkInTime(checkInTimeStringFormat)
 * @param checkOutTime(checkOutTimeStringFormat)
 * @returns
 */
function _Customer(id, name, phoneNumber, remark, furnish, peopleCount,
		checkInTime, checkOutTime, bookingList) {
	this.id = id;
	this.name = name;
	this.phoneNumber = phoneNumber;
	this.remark = remark;
	this.furnish = furnish;
	this.peopleCount = peopleCount;
	this.checkInTime = checkInTime;
	this.checkOutTime = checkOutTime;
	/** <bookingId, booking> */
	this.bookingList = new DataKeeper();
}

/**
 * Customer module.
 */
var Customer = (function() {
	var self = {};
	var dataUrl = App.URL + "customer/getDiningCustomer";
	/** <customerId, _Customer> */
	var customerData = new DataKeeper();

	self.init = function() {
		return App.ajax({
			url : dataUrl,
			success : function(data, textStatus, jqHXR) {
				var objData = data.data;
				$.each(objData, function(key, value) {
					/** furnish */
					var furnish = new _Furnish(value.furnish.furnishID, //
					value.furnish.name, //
					value.furnish.x, //
					value.furnish.y, //
					value.furnish.furnishClass.name //
					);

					/** customer */
					var customer = new _Customer(value.customerID, //
					value.customerName, //
					value.phoneNumber, //
					value.remark, //
					furnish, //
					value.peopleCount, //
					value.checkInTimeStringFormat, //
					null //
					);

					customerData.add(customer.id, customer);
				});
			}
		});
	};

	self.addCustomer = function(customerObj) {
		// update customerData & pulish
	}

	self.removeCustomer = function(customerId) {
		// update customerData & pulish
	}

	self.getAll = function() {
		return customerData.getAll();
	};

	return self;
})();