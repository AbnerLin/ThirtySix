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
 * Customer checkIn class.
 * 
 * @param customerName
 * @param customerPhone
 * @param peopleCount
 * @param furnishId
 * @returns
 */
function CheckInData(customerName, customerPhone, peopleCount, furnishId) {
	this.customerName = customerName;
	this.customerPhone = customerPhone;
	this.peopleCount = peopleCount;
	this.furnishId = furnishId;
};

/**
 * Customer class.
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
		checkInTime, checkOutTime) {
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
	var checkInUrl = App.URL + "customer/checkIn";
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
					var customer = new _Customer( //
					value.customerID, //
					value.customerName, //
					value.phoneNumber, //
					value.remark, //
					furnish, //
					value.peopleCount, //
					value.checkInTimeStringFormat, //
					null //
					);
					// TODO bookingList
					// TODO bookingList
					// TODO bookingList

					customerData.add(customer.id, customer);
				});
			}
		});
	};

	self.checkIn = function(customerObj) {
		return App.ajax({
			url : checkInUrl,
			data : customerObj,
			success : function(data, textStatus, jqHXR) {
				App.alertSuccess("Check in 成功！");
			}
		});
	};

	self.checkOut = function(customerId) {

	};

	self.getAll = function() {
		return customerData.getAll();
	};

	self.get = function(customerId) {
		return customerData.get(customerId);
	};

	/**
	 * Get _Customer by furnishId;
	 */
	self.getCustomerByFurnishId = function(furnishId) {
		var result = null;
		$.each(self.getAll(), function(key, value) {
			if (value.furnish.id == furnishId) {
				result = value;
				return false;
			}
		});
		return result;
	};

	/**
	 * Update buffer & publish.
	 * 
	 * @param customerObj(_Customer)
	 */
	self.addCustomer = function(customerObj) {
		customerData.add(customerObj.id, customerObj);

		App.publish("/customer/checkIn", [ customerObj ]);
	}

	/**
	 * Update buffer & publish.
	 * 
	 * @param customerId
	 */
	self.removeCustomer = function(customerId) {
		customerData.remove(customerId);

		App.publish("/customer/checkOut", [ customerId ]);
	}

	return self;
})();