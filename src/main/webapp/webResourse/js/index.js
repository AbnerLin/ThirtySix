/** cache dining customer list */
var diningCustomer = null;

/** cache click object id */
var cacheClickObjectId = null;

/** modal show up customerId cache */
var modalShowUpCacheCustomerID = null;

/**
 * 取得目前在用餐的顧客資訊
 */
function getDiningCustomer() {
	var action = "getDiningCustomer";

	$.ajax({
		url : App.URL + action,
		async : true,
		success : function(response, status, jqXHR) {
			var data = response.data;
			diningCustomer = data;

			/** init seatMap */
			initSeatMap(data);

			alertify.success("用餐中顧客載入成功!");
		}
	});
}

/**
 * 出場
 * 
 * @param customerData
 */
function checkOut() {
	var customerID = $("#orderCustomerId").val();
	var tableNumber = $("#orderTableNumber").val();

	alertify.confirm("確定結帳？", function(e) {
		if (e) {
			var action = "customerCheckOut";
			$.ajax({
				url : App.URL + action,
				async : true,
				method : "POST",
				data : {
					customerID : customerID,
					tableNumber : tableNumber
				},
				success : function(response, status, jqXHR) {
					alertify.success("checkOut 成功！");
				}
			});
		}
	});
}

/**
 * init seat icon after checkout. update buffer.
 */
function resetSeat(info) {
	var tableNumber = info.tableNumber;
	var customerID = info.customerID;
	$("#" + tableNumber).removeClass("diningTable");
	$("#" + tableNumber).addClass("emptyTable");
	$("#" + tableNumber).find(".seatMapBadge").remove();

	if (modalShowUpCacheCustomerID == customerID)
		$("#serviceModal").modal("hide");

	alertify.success("桌號" + tableNumber + "結帳出場！");

	delete diningCustomer[customerID];
}

/**
 * 進場
 * 
 * @param tableNumber
 */
function checkIn(tableNumber) {
	$("#" + tableNumber).removeClass("emptyTable");
	$("#" + tableNumber).addClass("diningTable");

	alertify.success("桌號：" + tableNumber + " 客人進場。");
}

/** 更新座位表 */
function updateSeatMapBadge(customerData) {

	/** add badge(unSend) */
	var id = customerData.tableNumber.trim();

	var unSendCount = 0;
	var bookingList = customerData.bookingList;
	$.each(bookingList, function(key, value) {
		if (value.isSend == "0")
			unSendCount++;
	});
	if (unSendCount > 0) {
		var h4 = document.createElement("h4");
		var badge = document.createElement("span");
		$(badge).addClass("label label-danger label-pill seatMapBadge");
		$(badge).html(unSendCount);
		$("<br>").appendTo("#" + id);
		$(badge).appendTo(h4);
		$(h4).appendTo("#" + id);
	} else {
		$("#" + id).find(".seatMapBadge").remove();
	}
}

/**
 * 判斷該桌號是否使用中
 * 
 * @param tableNumber
 */
function isTableDining(tableNumber) {
	for ( var key in diningCustomer) {
		if (diningCustomer.hasOwnProperty(key)) {
			var jsonObj = diningCustomer[key];
			var _tableNumber = jsonObj.tableNumber.trim();
			if (tableNumber == _tableNumber)
				return true;
		}
	}
	return false;
}

/**
 * 取得顧客編號
 * 
 * @param tableNumber
 */
function getCustomerIdByTableNumber(tableNumber) {
	for ( var key in diningCustomer) {
		if (diningCustomer.hasOwnProperty(key)) {
			var jsonObj = diningCustomer[key];
			if (jsonObj.tableNumber.trim() == tableNumber.trim())
				return jsonObj.customerID;
		}
	}
	return null;
}

/**
 * 更新用餐中顧客列表
 */
function updateDiningCustomerList(data) {

	$('#diningCustomerList').html("");

	for ( var key in data) {
		if (data.hasOwnProperty(key)) {
			var jsonObj = data[key];

			/** button */
			var buttonDiv = document.createElement("div");
			$(buttonDiv).attr({
				"customerId" : key,
				"tableNumber" : jsonObj.tableNumber,
				"data-toggle" : "modal",
				"data-target" : "#serviceModal"
			});
			$(buttonDiv).text(
					"顧客編號: " + key + " 桌號： " + jsonObj.tableNumber + " 人數："
							+ jsonObj.peopleCount + " 入場時間："
							+ jsonObj.checkInTime);
			$(buttonDiv).appendTo("#diningCustomerList");

			/** bookingList */
			var bookingList = document.createElement("div");
			var list = jsonObj.bookingList;
			$.each(list, function(key, value) {
				var booking = document.createElement("div");
				var itemName = value.item.name;
				var volume = value.volume;
				var isSend = value.isSend;
				$(booking).html(
						"項目：" + itemName + "*" + volume + " 出餐:" + isSend);
				$(booking).appendTo(bookingList);
			});
			$(bookingList).appendTo(buttonDiv);
		}
	}
}

/**
 * 新增顧客
 */
function customerCheckIn() {
	var customerName = $("#checkInCustomerName").val();
	var tableNumber = $("#checkInTableNumber").val();
	var peopleCount = $("#checkInPeopleCount").val();
	var phoneNumber = $("#checkInCustomerPhone").val();

	var confirmInfo = "顧客名稱： " + customerName + "<br>" + "顧客電話： " + phoneNumber
			+ "<br>" + "桌號： " + tableNumber + "<br>" + "人數： " + peopleCount;

	alertify.confirm(confirmInfo, function(e) {
		if (e) {
			var action = "customerCheckIn";
			$.ajax({
				url : App.URL + action,
				async : true,
				method : "POST",
				data : {
					customerName : customerName,
					tableNumber : tableNumber,
					phoneNumber : phoneNumber,
					peopleCount : peopleCount
				},
				success : function(response, status, jqXHR) {
					alertify.success("checkIn 成功！");

					/** hide modal */
					$("#checkInModal").modal("hide");
				}
			});
		}
	});
}

/**
 * 監聽webSocket
 */
function subscribeWebSocket() {
	var socket = new SockJS('/thirtySix/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);

		/** customer update listener */
		stompClient.subscribe('/topic/customerUpdate', function(data) {
			var jsonObj = JSON.parse(data.body);
			diningCustomer = jsonObj;
		});

		stompClient.subscribe('/topic/specifyCustomerUpdate', function(data) {
			/** update modal display data if show up. */
			var customerData = JSON.parse(data.body);

			/** update seat map */
			updateSeatMapBadge(customerData);

			/** update modal display data */
			if (modalShowUpCacheCustomerID == customerData.customerID)
				setServiceInfo(customerData);

			/** update buffer */
			diningCustomer[customerData.customerID] = customerData;
		});

		stompClient.subscribe('/topic/customerCheckIn', function(data) {
			checkIn(data.body);
		});

		stompClient.subscribe('/topic/customerCheckOut', function(data) {
			resetSeat(JSON.parse(data.body));
		});

		/** server time listener */
		stompClient.subscribe('/topic/time', function(data) {
			$("#serverTime").html(data.body);
		});
	});
}

/**
 * 取得菜單
 */
function getMenu() {
	var action = "getMenu";

	$.ajax({
		url : App.URL + action,
		async : true,
		method : "POST",
		success : function(response, status, jqXHR) {
			var data = response.data;
			alertify.success("菜單載入成功!");

			for ( var key in data) {
				if (data.hasOwnProperty(key)) {
					var jsonObj = data[key];

					/** panel container */
					var panel = document.createElement("div");
					$(panel).addClass("panel " + jsonObj.style);
					$(panel).appendTo("#menuBlock");

					/** panel header */
					var panelHeading = document.createElement("div");
					$(panelHeading).addClass("panel-heading");
					$(panelHeading).attr({
						"role" : "tab",
						"id" : "header" + jsonObj.classID
					});
					$(panelHeading).appendTo(panel);
					/** head button */
					var button = document.createElement("div");
					$(button).attr({
						"role" : "button",
						"data-toggle" : "collapse",
						"data-parent" : "#menuBlock",
						"href" : "#" + jsonObj.classID,
						"aria-expanded" : "true",
						"aria-controls" : jsonObj.classID
					});
					$(button).css("color", "black");
					$(button).html(jsonObj.className);
					$(button).appendTo(panelHeading);

					/** panel collapse */
					var panelCollapse = document.createElement("div");
					$(panelCollapse).addClass("panel-collapse collapse")
					$(panelCollapse).attr({
						"id" : jsonObj.classID,
						"role" : "tabpanel",
						"aria-labelledby" : "header" + jsonObj.classID
					});
					$(panelCollapse).appendTo(panel);

					/** panel body */
					var panelBody = document.createElement("div");
					$(panelBody).addClass("panel-body");
					$(panelBody).appendTo(panelCollapse);

					var itemList = jsonObj.itemList;
					var itemText = [];
					for (var index = 0; index < itemList.length; index++) {
						var item = itemList[index];

						/** row */
						var rowDiv = document.createElement("div");
						$(rowDiv).addClass("row text-center");
						$(rowDiv).appendTo(panelBody);

						/** food name */
						var nameDiv = document.createElement("div");
						$(nameDiv).addClass("col-md-3 col-sm-3 itemName");
						$(nameDiv).html(item.name);
						$(nameDiv).appendTo(rowDiv);

						/** input div */
						var inputDiv = document.createElement("div");
						$(inputDiv).addClass("col-md-9");
						$(inputDiv).appendTo(rowDiv);

						/** input group */
						var inputGroup = document.createElement("div");
						$(inputGroup).addClass("input-group");
						$(inputGroup).appendTo(inputDiv);

						/** minus input div */
						var minusDiv = document.createElement("div");
						$(minusDiv).addClass("input-group-btn");
						$(minusDiv).appendTo(inputGroup);

						/** minus button */
						var minusButton = document.createElement("button");
						$(minusButton).attr({
							"type" : "button",
							"aria-label" : "-1",
							"class" : "btn btn-success minusBtn"
						});
						var minusSpan = document.createElement("span");
						$(minusSpan).attr({
							"class" : "glyphicon glyphicon-minus-sign",
							"aria-hidden" : "true"
						});
						$(minusSpan).appendTo(minusButton);
						$(minusButton).appendTo(minusDiv);

						/** item id */
						var inputHidden = document.createElement("input");
						$(inputHidden).addClass("form-control itemId");
						$(inputHidden).attr({
							"type" : "hidden",
							"value" : item.itemID
						});
						$(inputHidden).appendTo(inputGroup);

						/** item volume */
						var inputVolume = document.createElement("input");
						$(inputVolume).addClass("form-control volume");
						$(inputVolume).attr("min", 0);
						$(inputVolume).attr({
							"type" : "number",
							"value" : 0
						});
						$(inputVolume).appendTo(inputGroup);

						/** plus input div */
						var plusDiv = document.createElement("div");
						$(plusDiv).addClass("input-group-btn");
						$(plusDiv).appendTo(inputGroup);

						/** plus button */
						var plusButton = document.createElement("button");
						$(plusButton).attr({
							"type" : "button",
							"aria-label" : "+1",
							"class" : "btn btn-danger plusBtn"
						});
						var plusSpan = document.createElement("span");
						$(plusSpan).attr({
							"class" : "glyphicon glyphicon-plus-sign",
							"aria-hidden" : "true"
						});
						$(plusSpan).appendTo(plusButton);
						$(plusButton).appendTo(plusDiv);
					}
				}
			}

			/** plus & minus btn trigger */
			$(".minusBtn").click(function() {
				var parentDiv = $(this).parents("div.input-group");
				var inputTag = $(parentDiv).find("input.volume")

				var volume = $(inputTag).val() - 1;
				if (volume < 0)
					volume = 0;
				$(inputTag).val(volume);
			});

			$(".plusBtn").click(function() {
				var parentDiv = $(this).parents("div.input-group");
				var inputTag = $(parentDiv).find("input.volume")

				var volume = parseInt($(inputTag).val()) + 1;
				$(inputTag).val(volume);
			});

			/** btn trigger and set to orderList */
			$(".minusBtn, .plusBtn").click(function() {
				inputTrigger();
			});

			/** volume input trigger */
			$(".volume").on("keypress keydown keyup", function() {
				inputTrigger();
			});

			function inputTrigger() {
				var orderListTxt = "";
				var inputs = $("#menuBlock").find("input.volume");
				$(inputs).each(function() {
					var volume = parseInt($(this).val());
					if (volume > 0) {
						var parentRow = $(this).closest("div.row");
						var nameDiv = $(parentRow).find(".itemName");
						orderListTxt += nameDiv.html() + "*" + volume + "<br>";
					}
				});
				$("#orderListInfo").html(orderListTxt);
			}
		}
	});
}

/** 下單 */
function sendOrder() {
	var customerId = $("#orderCustomerId").val();

	var itemList = [];
	var inputs = $("#menuBlock").find("input.volume");
	$(inputs).each(function() {
		var volume = parseInt($(this).val());
		if (volume > 0) {
			var parentRow = $(this).closest("div.row");
			var itemId = $(parentRow).find(".itemId").val();

			var innerObj = {
				"itemId" : itemId,
				"volume" : volume
			};
			itemList.push(innerObj);
		}
	});

	/** result */
	var orderObj = {
		"customerId" : customerId,
		"itemList" : itemList
	};

	if (itemList.length == 0) {
		alertify.error("請選擇餐點！");
		return;
	}

	/** confirm dialog */
	alertify.confirm("確定下單？", function(e) {
		if (e) {
			var action = "sendOrder";
			$.ajax({
				url : App.URL + action,
				async : true,
				method : "POST",
				dataType : "json",
				contentType : 'application/json',
				data : JSON.stringify(orderObj),
				success : function(response, status, jqXHR) {
					/** init input box */
					$(inputs).each(function() {
						$(this).val(0);
					});
					/** init order list info */
					$("#orderListInfo").html("");

					/** slide up panel */
					$("#menuBlock").find(".in").each(function() {
						$(this).removeClass("in");
					});

					alertify.success("下單成功！");
				}
			});
		}
	});

}

///**
// * table click event handler
// */
//function tableClickHandler() {
//	var tableNumber = $(this).attr("id");
//	var dining = isTableDining(tableNumber);
//
//	/** show up modal */
//	if (dining) {
//		/** set customerId, tableNumber */
//		var customerId = getCustomerIdByTableNumber(tableNumber);
//
//		/** cache modal customerId */
//		modalShowUpCacheCustomerID = customerId;
//
//		$("#orderTableNumber").val(tableNumber);
//		$("#orderCustomerId").val(customerId);
//
//		$("#serviceModal").modal("show");
//
//		$("#service-orderHistory").removeClass("in");
//
//		/** set value into service modal */
//		setServiceInfo(diningCustomer[customerId]);
//	} else {
//		$("#checkInTableNumber").val(tableNumber);
//
//		$("#checkInModal").modal("show");
//	}
//}

/**
 * set value into service modal
 */
function setServiceInfo(customerData) {
	/** clear previous display data */
	$("#orderListTable").html("");

	/** set value for order history. */
	var orderHistory = customerData.bookingList;

	var checkOutAmount = 0;
	var dataArray = [];
	$.each(orderHistory, function(key, value) {
		/** calc checkOutAmount */
		if (value.isSend == 1) {
			checkOutAmount += (value.volume * value.item.price);
		}

		/** build order history list */
		dataArray.push({
			itemName : value.item.name,
			orderTime : value.orderTimeStringFormat,
			deliveryTime : function() {
				if (value.deliveryTimeStringFormat != null)
					return value.deliveryTimeStringFormat;
				else
					return "未出餐";
			},
			volume : value.volume,
			send : function() {
				if (value.isSend == 0)
					return "block;";
				else
					return "none;";
			},
			isSend : value.isSend,
			bookingID : value.bookingID,
			customerID : key
		});
	});
	$("#orderListTableTemplate").tmpl(dataArray).appendTo("#orderListTable");
	$(".service-checkout-amount").html(checkOutAmount);

	/** hide isSend delivery */
	$("#orderListTable").find("tr").each(function() {
		if ($(this).attr("isSend") == 1)
			$(this).hide();
	});

	/** set value for customer info */
	$(".service-customerName").html(customerData.customerName);
	$(".service-peopleCount").html(customerData.peopleCount);
	$(".service-tableNumber").html(customerData.tableNumber);
	$(".service-checkInTime").html(customerData.checkInTimeStringFormat);
	$(".service-customerPhone").html(customerData.phoneNumber);
}

/**
 * show whether the dishes was send.
 * 
 * @param isDelivery
 */
function displayOrderList(isDelivery) {
	var isSend;
	if (isDelivery == true) {
		isSend = 1;
	} else {
		isSend = 0;
	}

	$("#orderListTable").find("tr").each(function() {
		if ($(this).attr("isSend") == isSend)
			$(this).show();
		else
			$(this).hide();
	});
}

// /** lock canvas */
// function lockMap() {
// /** show seat map revise option */
// $("#saveSeatMap, #deleteTable").fadeOut();
// // $("#imageSelection, #mapSizeOption").slideUp();
// $("#mapSetting").slideUp();
//
// /** disable draggable */
// $(".tableSeat").draggable("disable");
//
// /** click event */
// $(".tableSeat").click(tableClickHandler);
//
// /** hide garbage block */
// $("#garbageBlock").fadeOut();
//
// }

// function unlockMap() {
// /** show seat map revise option */
// $("#saveSeatMap, #deleteTable").fadeIn();
// // $("#imageSelection, #mapSizeOption").slideDown();
// $("#mapSetting").slideDown();
//
// /** map width trigger */
// $("#mapWidth").on("keypress keydown keyup", function() {
// $("#seatMap").css("width", $(this).val());
// });
//
// /** map height trigger */
// $("#mapHeight").on("keypress keydown keyup", function() {
// $("#seatMap").css("height", $(this).val());
// });
//
// /** disable click event */
// $(".tableSeat").unbind("click");
//
// /** apply draggable */
// $(".tableSeat").draggable("enable");
//
// /** display garbage block */
// $("#garbageBlock").fadeIn();
//
// /** add remove block */
// $("#garbageBlock").droppable({
// drop : function(event, ui) {
// var id = ui.draggable.attr("id");
// $("#" + id).remove();
//
// $("#garbageBlock").css({
// "background-color" : "#F0F0F0"
// });
//
// alertify.success("刪除桌號：" + id);
// },
// over : function(event, ui) {
// $("#garbageBlock").css({
// "background-color" : "#FF5733"
// });
// },
// out : function(event, ui) {
// $("#garbageBlock").css({
// "background-color" : "#F0F0F0"
// });
// }
// });
//
// /** remove tooltip */
// $(".tableSeat").tooltip('destroy');
//
// }

/**
 * 送餐
 * 
 * @param bookingID
 */
function sendItem(bookingID, customerID) {
	bookingID = bookingID.trim();

	var action = "sendDishes";
	$.ajax({
		url : App.URL + action,
		async : true,
		method : "POST",
		data : {
			"bookingID" : bookingID
		},
		success : function(response, status, jqXHR) {
			alertify.success("送餐成功！");
		}
	});

}
// /////////////////////////////////////////////////////////////////////////////
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
			//TODO
			console.log(event.data.furnishId);
			
			var furnishId = event.data.furnishId;
			
			var customerInfo = Customer.getCustomerByFurnishId(furnishId);
			console.log(customerInfo);
			
		};
		
		/**
		 * Item amount plus btn trigger.
		 */
		_export.amountPlus = function(btn) {
			var amount = parseInt($(btn).closest(".input-group").find(".itemAmount").val());
			amount += 1;
			$(btn).closest(".input-group").find(".itemAmount").val(amount);
			_export.updateOrderList();
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
			_export.updateOrderList();
		};
		
		/**
		 * Trigger input amount.
		 */
		_export.amountInputTrigger = function() {
			$(".itemAmount").on("input", function() {
				_export.updateOrderList();
			});
		};
		
		/**
		 * Update current order list.
		 */
		_export.updateOrderList = function() {
			//TODO
		};
		
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
