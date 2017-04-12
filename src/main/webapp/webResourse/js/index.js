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
			// $('#diningCustomerList').html(JSON.stringify(response));
			// console.log(JSON.stringify(response));
			// console.log(Object.keys(data).length);
			// console.log(Object.keys(data));

			var data = response.data;
			var customerList = "";
			for (var key in data) {
				if (data.hasOwnProperty(key)) {
					var jsonObj = data.get(key);
					customerList += "顧客編號: " + key + " 桌號： " + jsonObj.deskNumber + " 人數：" + jsonObj.peopleCount + " 入場時間：" + jsonObj.checkInTime;
				}
			}
			$('#diningCustomerList').html(customerList);
			
		}
	})
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
		success : function(data, status, jqXHR) {
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
		stompClient.subscribe('/topic/customerUpdate', function(data) {
			// showGreeting(JSON.parse(greeting.body).content);
			alert(JSON.stringify(data));
		});

		/** server time listener */
		stompClient.subscribe('/topic/time', function(data) {
			$("#serverTime").html(data.body);
		});
	});
}

$(document).ready(function() {
	/** 啟動監聽WebSocket */
	subscribeWebSocket();

});