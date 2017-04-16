var appUrl = "/thirtySix/";

/**
 * 取得目前在用餐的顧客資訊
 */
function getDiningCustomer() {
	var action = "getDiningCustomer";

	$.ajax({
		url : appUrl + action,
		async : true,
		dataType : "json",
		success : function(response, status, jqXHR) {
			var data = response.data;
			updateDiningCustomerList(data);
			alertify.success("用餐中顧客載入成功!");
		}
	})
}

/**
 * 更新用餐中顧客列表
 */
function updateDiningCustomerList(data) {
	// TODO
	$('#diningCustomerList').html("");

	for ( var key in data) {
		if (data.hasOwnProperty(key)) {
			var jsonObj = data[key];

			/** button */
			var buttonDiv = document.createElement("div");
			$(buttonDiv).attr({
				"customerId" : key,
				"tableNumber" : jsonObj.tableNumber,
				"class" : "table",
				"data-toggle" : "modal",
				"data-target" : "#myModal"
			});
			$(buttonDiv).text(
					"顧客編號: " + key + " 桌號： " + jsonObj.tableNumber + " 人數："
							+ jsonObj.peopleCount + " 入場時間："
							+ jsonObj.checkInTime);
			$(buttonDiv).appendTo("#diningCustomerList");

			// <button type="button" class="btn btn-primary btn-lg"
			// data-toggle="modal" data-target="#myModal">Launch demo modal
			// </button>

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

	/** 桌號點餐trigger */
	$("div.table").on("click", function() {
		var customerId = $(this).attr("customerId");
		var tableNumber = $(this).attr("tableNumber");

		$("#orderTableNumber").val(tableNumber);
		$("#orderCustomerId").val(customerId);
	});
}

/**
 * 新增顧客
 */
function customerCheckIn() {
	var action = "customerCheckIn";

	$.ajax({
		url : appUrl + action,
		async : true,
		method : "POST",
		data : {
			customerName : "ShaoYang, Lin",
			tableNumber : "A8",
			phoneNumber : "0987654321",
			peopleCount : 8
		},
		success : function(response, status, jqXHR) {
			// TODO

			// alert("success !!!");
			// alert(JSON.stringify(data));
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
			updateDiningCustomerList(jsonObj);
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

	var action = "sendOrder";
	$.ajax({
		url : appUrl + action,
		async : true,
		method : "POST",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(orderObj),
		success : function(response, status, jqXHR) {
			// TODO
			/** init input box */
			// TODO
			/** init table number */
			// TODO
			// alert(JSON.stringify(response));
		}
	});
}

/** init canvas */
function initCanvas() {
	/** init */
	var canvas = new fabric.Canvas("seatCanvas", {
		width : 800,
		height : 600
	});

	//TODO
	var moveHandler = function (evt) {
	    var movingObject = evt.target;
	    console.log(movingObject.get('left'), movingObject.get('top'));
	    //TODO img
	    
	    //TODO text
	    console.log(movingObject.item(1).getText());
	    
	    //TODO
	    //export to json ?
	};
	canvas.on('mouse:up', moveHandler);
	
	/** add obj trigger */
	$(".canvasInnerObj").click(function() {
		var id = $(this).attr("id");

		/** table number */
		var text = new fabric.Text("01", {
			fontFamily : 'Comic Sans',
			fontSize : 30
		});

		/** table img */
		var imgElement = document.getElementById(id);
		var img = new fabric.Image(imgElement, {
			id : "A1",
			opacity : 0.85
		});

		/** text position */
		text.set("top", (img.getBoundingRectHeight() / 2) - (text.width / 2));
		text.set("left", (img.getBoundingRectWidth() / 2) - (text.height / 2));

		var group = new fabric.Group([ img, text ], {
			left : 100,
			top : 25,
		});

		canvas.add(group);
	});
}

$(document).ready(function() {
	/** 啟動監聽WebSocket */
	subscribeWebSocket();

	/** 載入用餐中顧客 */
	getDiningCustomer();

	/** 載入菜單列表 */
	getMenu();

	/** init canvas */
	initCanvas();
});

// TODO
// 1. confirm submit order.
