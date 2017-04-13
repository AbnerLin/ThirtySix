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
	$('#diningCustomerList').html("");
	for ( var key in data) {
		var customerList = "";
		if (data.hasOwnProperty(key)) {
			var jsonObj = data[key];

			var div = document.createElement("div");
			$(div).attr("custmerId", key);
			$(div).attr("deskNumber", jsonObj.deskNumber);
			customerList += "顧客編號: " + key + " 桌號： " + jsonObj.deskNumber
					+ " 人數：" + jsonObj.peopleCount + " 入場時間："
					+ jsonObj.checkInTime;
			$(div).html(customerList);
			$(div).appendTo("#diningCustomerList");
		}
	}
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
			deskNumber : "A8",
			phoneNumber : "0911491788",
			peopleCount : 8
		},
		success : function(response, status, jqXHR) {
			// alert("success !!!");
			// alert(JSON.stringify(data));
		}
	})
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
//			$("#menu").html(JSON.stringify(data));
			for ( var key in data) {
				if (data.hasOwnProperty(key)) {
					var jsonObj = data[key];
					
					/** panel heading */
					var panelHeading = document.createElement("div");
					$(panelHeading).addClass("panel-heading");
					$(panelHeading).html(jsonObj.className);
					
					/** panel body */
					var panelBody = document.createElement("div");
					$(panelBody).addClass("panel-body");
					
					/** create input box */
					var itemList = jsonObj.itemList;
					var itemText = [];
					for(var index = 0; index < itemList.length; index++) {
						var item = itemList[index];
						
						itemText.push('<div class="row">');
						itemText.push('<div class="col-md-6">' + item.name + '</div>');
						itemText.push('<div class="col-md-6">');
						itemText.push('<input type="hidden" class="itemId" value="' + item.itemID + '" />');
						itemText.push('<input type="number" class="volume" value=0 />');
						itemText.push("</div>");
						itemText.push("</div>");
					}
					
//					<div class="panel-group" id="accordion" role="tablist"
//						aria-multiselectable="true">
//						<div class="panel panel-default">
//							<div class="panel-heading" role="tab" id="headingOne">
//								<h4 class="panel-title">
//									<a role="button" data-toggle="collapse" data-parent="#accordion"
//										href="#collapseOne" aria-expanded="true"
//										aria-controls="collapseOne"> Collapsible Group Item #1 </a>
//								</h4>
//							</div>
//							<div id="collapseOne" class="panel-collapse collapse in"
//								role="tabpanel" aria-labelledby="headingOne">
//								<div class="panel-body">Anim pariatur cliche reprehenderit,
//									enim eiusmod high life accusamus terry richardson ad squid. 3
//									wolf moon officia aute, non cupidatat skateboard dolor brunch.
//									Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
//									tempor, sunt aliqua put a bird on it squid single-origin coffee
//									nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica,
//									craft beer labore wes anderson cred nesciunt sapiente ea
//									proident. Ad vegan excepteur butcher vice lomo. Leggings
//									occaecat craft beer farm-to-table, raw denim aesthetic synth
//									nesciunt you probably haven't heard of them accusamus labore
//									sustainable VHS.</div>
//							</div>
//						</div>
//					</div>
					
					
					
					$(panelBody).html(itemText.join(""));
					
					/** panel container */
					var panel = document.createElement("div");
					$(panel).addClass("panel " + jsonObj.style);
					$(panel).appendTo("#menu");
					
					/** add to panel container */
					$(panelHeading).appendTo(panel);
					$(panelBody).appendTo(panel);
				}
			}

			// <div class="panel panel-default">
			// <div class="panel-heading">Panel heading without title</div>
			// <div class="panel-body">Panel content</div>
			// </div>

			// for ( var key in data) {
			// var customerList = "";
			// if (data.hasOwnProperty(key)) {
			// var jsonObj = data[key];
			//
			// var div = document.createElement("div");
			// $(div).attr("custmerId", key);
			// $(div).attr("deskNumber", jsonObj.deskNumber);
			// customerList += "顧客編號: " + key + " 桌號： " + jsonObj.deskNumber
			// + " 人數：" + jsonObj.peopleCount + " 入場時間："
			// + jsonObj.checkInTime;
			// $(div).html(customerList);
			// $(div).appendTo("#diningCustomerList");
			// }
			// }

		}
	})
}

$(document).ready(function() {
	/** 啟動監聽WebSocket */
	subscribeWebSocket();

	/** 載入菜單列表 */
	getMenu();
});