/**
 * User Auth
 */
var Auth = (function() {
	var self = {}

	var loginUrl = "login";
	var logoutUrl = "logout";
	var privilegeUrl = "auth/getUserPrivilege";

	self.getPrivilege = function() {
		$.ajax({
			url : App.URL + privilegeUrl,
			async : false,
			success : function(response, status, jqXHR) {
				return response.data;
			}
		});
	}

	self.login = function(username, password) {
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
					App.alertSuccess("登入成功，載入中..");
					setTimeout(function() {
						window.location.replace(App.URL);
					}, 1000);
				} else {
					App.alertError("帳密錯誤！");
				}
			}
		});
	}

	self.logout = function() {
		$.ajax({
			url : App.URL + logoutUrl,
			method : "POST",
			async : true,
			success : function(response, status, jqXHR) {
				App.alertSuccess("登出中。");
				setTimeout(function() {
					window.location.replace(App.URL);
				}, 1000);
			}
		});
	}

	return self;
})();
