/**
 * User Auth
 */
var Auth = (function() {
	var self = {}

	var loginUrl = "login";
	var logoutUrl = "logout";
	var privilegeUrl = "auth/getUserPrivilege";

	self.getPrivilege = function() {
		App.ajax({
			url : App.URL + privilegeUrl,
			async : false,
			success : function(response, status, jqXHR) {
				return response.data;
			}
		});
	}

	self.login = function(username, password) {
		return App.ajax({
			url : App.URL + loginUrl,
			data : {
				username : username,
				password : password
			},
			success : function(response, status, jqXHR) {
				if (response == true) {
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
		App.ajax({
			url : App.URL + logoutUrl,
			dataType : "text",
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
