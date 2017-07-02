var appUrl = "/thirtySix/";

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
		url : appUrl + action,
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
				url : appUrl + action,
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
	// TODO ui must update..
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
				url : appUrl + action,
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
		url : appUrl + action,
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
				url : appUrl + action,
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

/**
 * table click event handler
 */
function tableClickHandler() {
	var tableNumber = $(this).attr("id");
	var dining = isTableDining(tableNumber);

	/** show up modal */
	if (dining) {
		/** set customerId, tableNumber */
		var customerId = getCustomerIdByTableNumber(tableNumber);

		/** cache modal customerId */
		modalShowUpCacheCustomerID = customerId;

		$("#orderTableNumber").val(tableNumber);
		$("#orderCustomerId").val(customerId);

		$("#serviceModal").modal("show");

		$("#service-orderHistory").removeClass("in");

		/** set value into service modal */
		setServiceInfo(diningCustomer[customerId]);
	} else {
		$("#checkInTableNumber").val(tableNumber);

		$("#checkInModal").modal("show");
	}
}

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

/** lock canvas */
function lockMap() {
	/** show seat map revise option */
	$("#saveSeatMap, #deleteTable").fadeOut();
	// $("#imageSelection, #mapSizeOption").slideUp();
	$("#mapSetting").slideUp();

	/** disable draggable */
	$(".tableSeat").draggable("disable");

	/** click event */
	$(".tableSeat").click(tableClickHandler);

	/** hide garbage block */
	$("#garbageBlock").fadeOut();

}

function unlockMap() {
	/** show seat map revise option */
	$("#saveSeatMap, #deleteTable").fadeIn();
	// $("#imageSelection, #mapSizeOption").slideDown();
	$("#mapSetting").slideDown();

	/** map width trigger */
	$("#mapWidth").on("keypress keydown keyup", function() {
		$("#seatMap").css("width", $(this).val());
	});

	/** map height trigger */
	$("#mapHeight").on("keypress keydown keyup", function() {
		$("#seatMap").css("height", $(this).val());
	});

	/** disable click event */
	$(".tableSeat").unbind("click");

	/** apply draggable */
	$(".tableSeat").draggable("enable");

	/** display garbage block */
	$("#garbageBlock").fadeIn();

	/** add remove block */
	$("#garbageBlock").droppable({
		drop : function(event, ui) {
			var id = ui.draggable.attr("id");
			$("#" + id).remove();

			$("#garbageBlock").css({
				"background-color" : "#F0F0F0"
			});

			alertify.success("刪除桌號：" + id);
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

	/** remove tooltip */
	$(".tableSeat").tooltip('destroy');

}

/**
 * init seat map
 */
function initSeatMap(customerData) {
	var action = "getSeatMap";

	$.ajax({
		url : appUrl + action,
		async : true,
		method : "POST",
		success : function(response, status, jqXHR) {
			var data = response.data[0];
			if (data == null)
				return;

			/** set map ID */
			var mapID = data.mapID;
			$("#mapID").val(mapID)

			/** set location */
			var location = data.location;
			$("#mapLocation").val(location);

			/** set width */
			var width = parseInt(data.width);
			$("#mapWidth").val(width);

			/** set height */
			var height = parseInt(data.height);
			$("#mapHeight").val(height);

			/** resize seat map */
			$("#seatMap").animate({
				width : width,
				height : height
			}, 800);

			/** set funish position */
			$.each(data.seatPositionList, function(key, value) {
				addTableToMap(value.displayText, value.x, value.y);
			});
			lockMap();
			initInfoToSeatMap(customerData);
		}
	});

	/** init adjust toggle */
	$("#seatMap-toggle").bootstrapToggle();
	$('#seatMap-toggle').change(function() {
		var checked = $(this).prop("checked");
		if (checked == true) {
			unlockMap();
		} else if (checked == false) {
			lockMap();
		}
	});

	/** compoment add */
	$(".mapOption").click(function() {

		/** get table number */
		alertify.prompt("請輸入桌號", function(e, str) {
			if (e) {
				tableNumber = str.trim();

				if (tableNumber == "") {
					alertify.alert("桌號不可空白！");
					return;
				}

				/** check tableNumber duplicate */
				var isDuplicate = false;
				$(".tableSeat").each(function() {
					var _id = $(this).attr("id").trim();
					if (_id == tableNumber) {
						alertify.alert("桌號重複！");
						isDuplicate = true;
					}
				});

				if (!isDuplicate)
					addTableToMap(tableNumber, 0, 0);
			}
		}, "");
	});

	/** disable draggable */
	lockMap();
}

function initInfoToSeatMap(data) {
	/** set info to seat map */
	$(".tableSeat").each(function() {
		var id = $(this).attr("id");

		/** switch image */
		var isDining = isTableDining(id);
		if (isDining) {
			$(this).removeClass("emptyTable");
			$(this).addClass("diningTable");
		} else {
			$(this).removeClass("diningTable");
			$(this).addClass("emptyTable");
		}

		/** unSend badge */
		var customerID = getCustomerIdByTableNumber(id);
		if (customerID == null)
			return;

		var unSendCount = 0;
		var bookingList = data[customerID].bookingList;
		$.each(bookingList, function(key, value) {
			if (value.isSend == "0")
				unSendCount++;
		});
		if (unSendCount > 0) {
			var h4 = document.createElement("h4");
			var badge = document.createElement("span");
			$(badge).addClass("label label-danger label-pill");
			$(badge).html(unSendCount);
			$("<br>").appendTo($(this));
			$(badge).appendTo(h4);
			$(h4).appendTo($(this));
		}
	});

}

/**
 * 新增桌號至地圖
 * 
 * @param tableNumber
 */
function addTableToMap(tableNumber, x, y) {

	var className = "";

	/** container */
	var container = document.createElement("div");
	$(container).addClass("emptyTable tableSeat disableSelection");
	$(container).html(tableNumber);

	/** set Id */
	$(container).attr({
		id : tableNumber
	});

	/** set position */
	$(container).css({
		"top" : y,
		"left" : x
	});

	$(container).draggable({
		containment : "#seatMap",
		zIndex : 1
	});

	$(container).css("position", "absolute");

	/** add child to map */
	$("#seatMap").append(container);
}

/**
 * 儲存座位表
 */
function saveSeatMap() {
	var mapID = $("#mapID").val();
	var mapLocation = $("#mapLocation").val();
	var mapWidth = $("#mapWidth").val();
	var mapHeight = $("#mapHeight").val();

	/** compose container list */
	var containerList = [];
	$(".tableSeat").each(function() {
		containerList.push({
			x : $(this).position().left,
			y : $(this).position().top,
			displayText : $(this).attr("id"),
		});
	});

	var postData = {
		mapID : mapID,
		location : mapLocation,
		width : mapWidth,
		height : mapHeight,
		seatPositionList : containerList
	}

	var action = "saveSeatMap";
	$.ajax({
		url : appUrl + action,
		async : true,
		method : "POST",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(postData),
		success : function(response, status, jqXHR) {
			alertify.success("座位表儲存成功！");
		}
	});

}

/**
 * 送餐
 * 
 * @param bookingID
 */
function sendItem(bookingID, customerID) {
	bookingID = bookingID.trim();

	var action = "sendDishes";
	$.ajax({
		url : appUrl + action,
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

/**
 * Load furnish option.
 */
function getFurnishOption() {
	var action = "getFurnishClass";
	$.ajax({
		url : appUrl + action,
		async : true,
		success : function(response, status, jqXHR) {
			console.log(JSON.stringify(response));
		}
	});
}

$(document).ready(function() {
	/** 啟動監聽WebSocket */
	subscribeWebSocket();

	/** load furnish option */
	getFurnishOption();

	/** 載入菜單列表 */
	getMenu();

	/** get dining customer */
	getDiningCustomer();

});
