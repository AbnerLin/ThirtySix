function login() {
	$.ajax({
		url : appUrl + "login",
		method : "POST",
		async : true,
		data : {
			username : $("#username").val(),
			password : $("#password").val()
		},
		success : function(response, status, jqXHR) {

			if (response == "true") {
				window.location.replace(appUrl);
			} else {
				$("#msg").show();
			}
		}
	});
}