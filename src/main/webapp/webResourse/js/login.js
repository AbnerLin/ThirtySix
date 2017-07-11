function login() {
	$.ajax({
		url : App.URL + "login",
		method : "POST",
		async : true,
		data : {
			username : $("#username").val(),
			password : $("#password").val()
		},
		success : function(response, status, jqXHR) {

			if (response == "true") {
				window.location.replace(App.URL);
			} else {
				$("#msg").show();
			}
		}
	});
}