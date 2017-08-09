/**
 * Extends Map module.
 */
var Map = (function(self) {

	self._init = function() {
		mapSocketTrigger();
		setFurnishOption();
		mapSettingToggleTrigger();
		garbageBlockTrigger();
		mapSizeInputTrigger();

		/** trigger map info update. */
		App.subscribe("/map/update", function(event, map) {
			mapUpdate(map);
		});

		/** trigger furnish add */
		App.subscribe("/furnish/add", function(event, mapId, obj) {
			addFurnishToMap(mapId, obj);
		});

		/** trigger furnish remove */
		App.subscribe("/furnish/remove", function(event, mapId, objId) {
			removeFurnishFromMap(mapId, objId)
		});

		return self.init();
	};

	/**
	 * Refresh furnish by furnishId
	 */
	self.refresh = function(furnishId) {
		var dom = $("#" + furnishId);
		if (dom.length <= 0)
			return;

		if (dom.hasClass("TABLE") || dom.hasClass("EMPTY_TABLE")) {
			if (Customer.getCustomerByFurnishId(furnishId)) {
				/** css */
				dom.css("background-image", "url("
						+ FurnishClass.data.get("TABLE").imagePath + ")");
				dom.removeClass("EMPTY_TABLE").addClass("TABLE");
				
				/** Click trigger. */
				dom.off("click").on("click", {furnishId : furnishId}, Menu.serviceModal.show);
			} else if (!$("#seatMap-toggle").prop("checked")) {
				/** css */
				dom.css("background-image", "url("
						+ FurnishClass.data.get("EMPTY_TABLE").imagePath + ")");
				dom.removeClass("TABLE").addClass("EMPTY_TABLE");

				/** Click trigger. */
				dom.off("click").on("click", {furnishId : furnishId}, Customer.checkInModal.show);
			}
		}
	};

	/**
	 * Lock map setting.
	 */
	self.lock = function() {
		$(".mapSettingTool").fadeOut();
		$("#mapSetting").slideUp();

		/** disable draggable */
		$(".furnish").draggable("disable");

		/** Enable click event */
		$(".furnish").each(function() {
			var id = $(this).attr("id");
			self.refresh(id);
		});
	};

	/**
	 * Unlock map that can setting.
	 */
	self.unlock = function() {
		$(".mapSettingTool").fadeIn();
		$("#mapSetting").slideDown();

		/** enable draggable */
		$(".furnish").draggable("enable");
		
		/** disable click event */
		$(".furnish").unbind("click");
		
		/** reset icon */
		$(".furnish.EMPTY_TABLE").each(function() {
			$(this).animate({
				"opacity" : 0
			}, 500, function() {
				$(this).css("background-image", "url(" 
						+ FurnishClass.data.get("TABLE").imagePath
						+ ")");
				$(this).animate({
					"opacity" : 1
				}, 500);
			});
		});
	};

	/**
	 * Map option click trigger.
	 * 
	 * @param self
	 * @returns
	 */
	self.mapOptionClick = function mapOptionClick(self) {
		var _class = $(self).attr("enum");
		var nameable = $(self).attr("nameable") === "true";

		/** compose map obj. */
		var mapId = $("#mapId").val() || (function() {
			var id = App.uuid();
			$("#mapId").val(id);
			return id;
		})();
		var map = new _Map(mapId, "", $("#mapWidth").val(), $("#mapHeight")
				.val());

		/** create obj */
		var furnish = new _Furnish(App.uuid(), furnishAlias, 0, 0, _class);

		var furnishAlias = "";
		if (nameable) {
			alertify.prompt("請輸入桌號", function(e, str) {
				if (e) {
					furnishAlias = str.trim();

					if (furnishAlias == "") {
						App.alertError("桌號不可空白！");
						return;
					}

					var isDuplicate = false;
					if (Map.getAllFurnish(mapId)) {
						$.each(Map.getAllFurnish(mapId), function(key, value) {
							if (furnishAlias == key) {
								isDuplicate = true;
								return;
							}
						});
					}

					if (isDuplicate) {
						App.alertError("桌號重複！");
					} else {
						furnish.alias = furnishAlias;
						Map.addFurnish(map, furnish, true);
					}
				}
			}, "");
		} else {
			Map.addFurnish(map, furnish, true);
		}
	}

	/**
	 * Save decorator.
	 * 
	 * @returns
	 */
	self._save = function(btn) {
		App.showLoadingByBtn($("#mapBlock"), $(btn));
		Map.save().done(function() {
			App.hideLoadingByBtn($("#mapBlock"), 1000, $(btn));
		});
		
		$("#seatMap-toggle").prop("checked", false);
		Map.lock();
	};

	/**
	 * Load Map by map id.
	 * 
	 * @param mapId
	 * @returns
	 */
	self.loadMap = function(mapId) {
		var map = Map.getMap(mapId);

		$("#mapId").val(map.id);
		$("#mapLocation").val(map.name);
		$("#mapWidth").val(map.width);
		$("#mapHeight").val(map.height);

		mapResize(map.width, map.height);

		var delayEffect = 0, completeCount = 0;
		$.each(map.furnishList.data, function(key, value) {
			setTimeout(function() {
				decorator(function() {
					App.publish("/furnish/add", [ map.id, value ]);
				});
			}, delayEffect += 60);
		});

		/** effect decorator */
		function decorator(handler) {
			handler();
			if(++completeCount == Object.keys(map.furnishList.data).length) {
				self.lock();
				$("#option").slideDown();
			}
		}
	}

	/**
	 * Trigger size input tag.
	 * 
	 * @returns
	 */
	function mapSizeInputTrigger() {
		$("#mapWidth, #mapHeight").on("keypress keydown keyup", function() {
			mapResize($("#mapWidth").val(), $("#mapHeight").val());
		});

		$("#mapWidth, #mapHeight").on(
				"focusout",
				function() {
					var mapId = $("#mapId").val() || (function() {
						var id = App.uuid();
						$("#mapId").val(id);
						return id;
					})();
					var map = new _Map(mapId, $("#mapLocation").val(), $(
							"#mapWidth").val(), $("#mapHeight").val());
					Map.saveOrUpdateMap(map);
				});
	}

	function mapResize(width, height) {
		$("#seatMap").animate({
			"width" : width,
			"height" : height
		}, 500);
	}

	/**
	 * Update map info(Name, Width, Height).
	 */
	function mapUpdate(map) {
		if ($("#mapId").val() == map.id) {
			$("#mapLocation").val(map.name);
			mapResize(map.width, map.height);
		}
	}

	/**
	 * Socket trigger for seat map.
	 * 
	 * @returns
	 */
	function mapSocketTrigger() {
		/** trigger webSocket (update seat map) */
		WebSocket.subscribe("/topic/updateSeatMap", function(data) {
			var obj = JSON.parse(data.body);

			$.each(obj, function(key, value) {
				var map = new _Map(value.mapID, "", value.width, value.height);
				if ($("#mapId").val() != map.id) {
					return true;
				}

				/** redraw map and update map info */
				Map.saveOrUpdateMap(map);

				/** draw furnish. */
				$.each(value.newFurnishList, function(key, value) {
					var newFurnish = new _Furnish( //
					value.furnishID, //
					value.name, //
					value.x, //
					value.y, //
					FurnishClass.getEnumNameById(value.furnishClassID) //
					);
					Map.addFurnish(map, newFurnish, false);
				});

				/** remove furnish */
				$.each(value.removeFurnishList, function(key, value) {
					Map.removeFurnish(map.id, value, false);
				});
			});
		});
	}

	/**
	 * Set Furnish Option.
	 * 
	 * @returns
	 */
	function setFurnishOption() {
		FurnishClass.init().done(
				function() {
					var dataArray = [];
					$.each(FurnishClass.data.getAll(), function(key, value) {
						if (value.isVisible == true)
							dataArray.push(value);
					});
					$("#mapOptionTemplate").tmpl(dataArray).appendTo(
							"#imageSelection");
				});
	}

	function mapSettingToggleTrigger() {
		$('#seatMap-toggle').change(function() {
			var checked = $(this).prop("checked");
			if (checked == true) {
				self.unlock();
			} else if (checked == false) {
				self.lock();
			}
		});
	}

	/**
	 * Remove furnish from map.
	 * 
	 * @param mapId
	 * @param obj
	 * @returns
	 */
	function removeFurnishFromMap(mapId, objId) {
		if ($("#mapId").val() == mapId)
			$("#" + objId).fadeOut();
	}

	/**
	 * Add furnish to map.
	 * 
	 * @param mapId
	 * @param obj
	 * @returns
	 */
	function addFurnishToMap(mapId, obj) {
		if ($("#mapId").val() != mapId)
			return;

		$("#" + obj.id).remove();
		var container = document.createElement("div");
		$(container).html(obj.alias);
		$(container).attr({
			"class" : obj._class + " furnish",
			"id" : obj.id,
		}).css(
				{
					"top" : obj.y,
					"left" : obj.x,
					"position" : "absolute",
					"background-image" : "url("
							+ FurnishClass.data.get(obj._class).imagePath + ")"
				}).draggable({
			containment : "#seatMap",
			zIndex : 1,
			start : function(event, ui) {
				var furnish = Map.getFurnish(mapId, obj.id);
				furnish.oriX = ui.position.left;
				furnish.oriY = ui.position.top;
			},
			stop : function(event, ui) {
				if (Map.getFurnish(mapId, obj.id)) {
					var furnish = Map.getFurnish(mapId, obj.id);
					furnish.x = ui.position.left;
					furnish.y = ui.position.top;
					Map.updateFurnish(Map.getMap(mapId), furnish);
				}
			}
		});
		$("#seatMap").append($(container).hide().fadeIn());

		/** refresh status */
		self.refresh(obj.id);
	}

	function garbageBlockTrigger() {
		$("#garbageBlock").droppable({
			drop : function(event, ui) {
				var id = ui.draggable.attr("id");

				$("#" + id).draggable("disable");
				
				/** Check whether table is using. */
				if(!Customer.getCustomerByFurnishId(id))
					Map.removeFurnish($("#mapId").val(), id, true);
				else {
					App.alertError("該桌使用中。");

					setTimeout(function() {
						var mapId = $("#mapId").val();
						var furnish = Map.getFurnish(mapId, id);
						furnish.x = furnish.oriX;
						furnish.y = furnish.oriY;
						Map.updateFurnish(Map.getMap(mapId), furnish);
					}, 100);
				}
				
				$("#garbageBlock").css({
					"background-color" : "#F0F0F0"
				});
			},
			over : function(event, ui) {
				$("#garbageBlock").css({
					"background-color" : "#FF5733"
				});
			},
			out : function(event, ui) {
				$("#garbageBlock").css({
					"background-color" : "#F0F0F0"
				});
			}
		});
	}

	/**
	 * @returns
	 */
	function emptyTableClickHandler() {
		console.log("empty table.");
	}

	/**
	 * @returns
	 */
	function usingTableClickHandler() {
		console.log("using table.");
	}

	return self;
})(Map);

/**
 * Extends Customer module.
 */
var Customer = (function(self) {

	self._init = function() {
		customerSocketTrigger();

		/** trigger customer checkIn */
		App.subscribe("/customer/checkIn", function(event, obj) {
			/** Update seatmap */
			var furnishId = obj.furnish.id;
			Map.refresh(furnishId);
		});		
		
		App.subscribe("/customer/checkOut", function(event, customerId) {
			//TODO
		});
		
		/** trigger customer send order. */
		App.subscribe("/customer/sendOrder", function(event, customerId, bookingList) {
			//TODO  1. update order history.. 2. update furnish badge.
		});
		
		return self.init();
	};
	
	/**
	 * Check In Modal.
	 */
	self.checkInModal = (function() {
		var _export = {};
		
		/**
		 * Show up.
		 */
		_export.show = function(event) {
			$("#checkInFurnishID").val(event.data.furnishId);
			$("#checkInModal").modal("show");
		};
		
		_export.hide = function() {
			$("#checkInModal").modal("hide");
		};
		
		_export.peopleCountAddBtn = function() {
			var peopleCount = parseInt($("#checkInPeopleCount").val());
			peopleCount += 1;
			$("#checkInPeopleCount").val(peopleCount);
		};
		
		_export.peopleCountMinusBtn = function() {
			var peopleCount = parseInt($("#checkInPeopleCount").val());
			peopleCount -= 1;
			if(peopleCount < 1)
				peopleCount = 1;
			$("#checkInPeopleCount").val(peopleCount);
		};
		
		_export.checkIn = function() {
			alertify.confirm("確定進場？", function(e) {
				if (e) {
					var customerInfo = new CheckInData(
							$("#checkInCustomerName").val(),
							$("#checkInCustomerPhone").val(),
							$("#checkInPeopleCount").val(),
							$("#checkInFurnishID").val()
						);
					
					Customer.checkIn(customerInfo);
					Customer.checkInModal.hide();
				}
			});
		};
		
		return _export;
	})();

	/**
	 * @param customerId
	 */
	self.checkOut = function(customerId) {
		// TODO
	};

	/**
	 * Trigger socket for customer.
	 * 
	 * @returns
	 */
	function customerSocketTrigger() {
		/** trigger webSocket (Update customer module) */
		WebSocket.subscribe("/topic/customerCheckIn", function(data) {
			var obj = JSON.parse(data.body);
			
			var customer = new _Customer(
				obj.customerID, //
				obj.customerName, //
				obj.phoneNumber, //
				obj.remark, //
				Map.getFurnishById(obj.furnish.furnishID), //
				obj.peopleCount, //
				obj.checkInTimeStringFormat,
				obj.checkOutTimeStringFormat,
			);
			
			Customer.addCustomer(customer);
		});
		
		WebSocket.subscribe("/topic/customerCheckOut", function(data) {
			//TODO
		});
		
		/** trigger customer send order. */
		WebSocket.subscribe("/topic/customerSendOrder", function(data) {
			var obj = JSON.parse(data.body);
			
			Customer.addBooking(obj.customerId, obj.bookingList);
		});
	}

	return self;
})(Customer);


/**
 * Extends Menu module.
 */
var Menu = (function(self) {
	
	self._init = function() {
		self.init().done(function() {
			loadMenu();
			self.serviceModal.amountInputTrigger();
		});
	};
	
	self.serviceModal = (function() {
		var _export = {};
		
		/**
		 * Show up.
		 */
		_export.show = function(event) {
			$("#serviceModal").modal("show");
			var furnishId = event.data.furnishId;
			var customerInfo = Customer.getCustomerByFurnishId(furnishId);
			
			/** Set order tmp id. */
			$("#orderTmpCustomerId").val(customerInfo.id);
			
			/** Load order history to page. */
			console.log(customerInfo);
		};
		
		/**
		 * Item amount plus btn trigger.
		 */
		_export.amountPlus = function(btn) {
			var amount = parseInt($(btn).closest(".input-group").find(".itemAmount").val());
			amount += 1;
			$(btn).closest(".input-group").find(".itemAmount").val(amount);
			_export.updateTmpOrderList($(btn));
		};
		
		/**
		 * Item amount minus btn trigger.
		 */
		_export.amountMinus = function(btn) {
			var amount = parseInt($(btn).closest(".input-group").find(".itemAmount").val());
			amount -= 1;
			if(amount < 0)
				amount = 0;
			$(btn).closest(".input-group").find(".itemAmount").val(amount);
			_export.updateTmpOrderList($(btn));
		};
		
		/**
		 * Trigger input amount.
		 */
		_export.amountInputTrigger = function() {
			$(".itemAmount").on("input", function() {
				_export.updateTmpOrderList($(this));
			});
		};
		
		/**
		 * Update current order list.
		 */
		_export.updateTmpOrderList = function(element) {
			var amount = $(element).closest(".input-group").find(".itemAmount").val();
			var itemId = $(element).closest(".input-group").find(".itemId").val();
			
			var obj = {
				id : itemId,
				name : Menu.getItemByItemId(itemId).name,
				amount : amount
			};
			
			if($("#" + itemId).length > 0) {
				if(amount <= 0) {
					$("#" + itemId).remove();
				} else {
					$("#" + itemId).find(".itemAmountOfOrder").val(amount);
					$("#" + itemId).find(".itemAmountOfOrderText").html(amount);	
				}
			} else if(amount > 0) {
				$("#itemOfOrderTemplate").tmpl(obj).appendTo("#orderTmpList");
				$("#sendOrderBtn").removeAttr("disabled");
			}
			if($(".itemAmountOfOrder").length <= 0) 
				$("#sendOrderBtn").attr("disabled", "disabled");

			/** Update total cost */
			var totalCost = 0;
			$(".itemOfOrder").each(function() {
				var itemId = $(this).find(".itemIdOfOrder").val();
				var itemAmount = parseInt($(this).find(".itemAmountOfOrder").val());
				totalCost += (Menu.getItemByItemId(itemId).price * itemAmount); 
			});
			
			$("#totalCost").html(totalCost);
		};
		
		/**
		 * Load order history to tab.
		 */
		function loadOrderHistory(customerInfo) {
			
		}
		
		return _export;
	})();
	
	/**
	 * Load menu to page.
	 */
	function loadMenu() {
		/** Load menu to page. */
		var dataArray = [];
		$.each(Menu.getAll(), function(key, value) {
			$.each(value.itemMap.data, function(innerKey, innerValue) {
				// Element must begin with letter, so Use a for begin.
				innerValue.eleId = "a" + innerValue.id.replace(new RegExp("-", "g"), "");
			});
			dataArray.push(value);
		});
		$("#menuTemplate").tmpl(dataArray).appendTo("#itemMenu");
	}
	
	return self;
})(Menu);


/**
 * Extends Order module.
 */
var Order = (function(self) {
	
	self._sendOrder = function(btn) {
		App.showLoading($("#serviceModalContent"));
		
		
		var customerId = $("#orderTmpCustomerId").val();
		var orderList = [];
		
		$(".itemOfOrder").each(function() {
			var itemId = $(this).find(".itemIdOfOrder").val();
			var itemAmount = $(this).find(".itemAmountOfOrder").val();
			
			orderList.push({
				itemId : itemId,
				amount : itemAmount
			});
		});
		
		/** Invoke super method */
		Order.sendOrder({
			customerId : customerId, //
			itemList : orderList //
		}, $(btn)).done(function() {
			$(".itemOfOrder").fadeOut(500, function() {
				$(this).remove();
			});
			$("#totalCost").html(0);
			$(".itemAmount").val(0);
				
			App.hideLoading($("#serviceModalContent"), 600);
		});
	};
	
	return self;
})(Order);

/**
 * Index page initialize.
 */
function init() {
	/** Menu init. */
	Menu._init();

	/** Customer init; Map init. */
	$.when(Map._init(), Customer._init()).done(function() {
		var mapData = Map.getAll();
		if (mapData) {
			Map.loadMap(Object.keys(mapData)[0]);
		}
	});
	
	/** Subscribe server time. */
//	App.subscribeServerTime();
//	App.subscribe("/topic/time", function(event, time) {
//		console.log(time);
//	});
	
	/***************************/
}
