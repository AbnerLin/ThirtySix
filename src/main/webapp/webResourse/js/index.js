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
		success : function(data, status, jqXHR) {
			$('#diningCustomerList').html(JSON.stringify(data));
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
//			alert("success !!!");
			alert(JSON.stringify(data));
		}
	})
}

$(document).ready(function() {
	
});