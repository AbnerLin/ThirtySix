var appUrl = "/thirtySix/";

function login() {
	
//	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
//	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
//	var csrfToken = $("meta[name='_csrf']").attr("content");
			
			
//	console.log(csrfParameter + "\n");
//	console.log(csrfHeader + "\n");
//	console.log(csrfToken + "\n");
//	return;
	
	$.ajax({
//		url : appUrl + "j_spring_security_check",
		url : appUrl + "login",
		method : "POST",
		async : true,
//		beforeSend : function(xhr) {
//			xhr.setRequestHeader(csrfHeader, csrfToken);
//		},
		data : {
			username : $("#username").val(),
			password : $("#password").val()
		},
		success : function(response, status, jqXHR) {
			alert(response);
		}
	});
}