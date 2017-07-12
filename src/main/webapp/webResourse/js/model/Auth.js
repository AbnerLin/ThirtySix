/**
 * User Auth
 */
var Auth = (function() {
	var loginUrl = "login";
	var logoutUrl = "logout";
	var privilegeUrl = "auth/getUserPrivilege";

	function getPrivilege() {
		$.ajax({
			url : App.URL + privilegeUrl,
			async : false,
			success : function(response, status, jqXHR) {
				return response.data;
			}
		});
	}

	function login(username, password) {
		$.ajax({
			url : App.URL + loginUrl,
			method : "POST",
			async : true,
			data : {
				username : username,
				password : password
			},
			success : function(response, status, jqXHR) {
				if (response == "true") {
					App.alertSuccess("Login Successed!!! Welcome^^~");
					setTimeout(function() {
						window.location.replace(App.URL);
					}, 1000);
				} else {
					App.alertError("<strong>Oops!!!</strong>"
							+ "Username and Password not accepted.");
				}
			}
		});
	}

	function logout() {
		$.ajax({
			url : App.URL + logoutUrl,
			method : "POST",
			async : true,
			success : function(response, status, jqXHR) {
				App.alertSuccess("Logout Successed!!! ByeBye^^~");
				setTimeout(function() {
					window.location.replace(App.URL);
				}, 1000);
			}
		});
	}

	return {
		login : login,
		logout : logout,
		getPrivilege : getPrivilege
	}
})();
