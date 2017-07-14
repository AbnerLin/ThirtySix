var App = (function() {
	self = {};

	var rootURL = "/thirtySix/";

	self.URL = rootURL;

	self.loadBaseJS = function(callback) {
		$LAB.script("js/core/common.js").wait() //
		.script("js/module/Auth.js") //
		.script("js/module/Furnish.js") //
		.script("js/module/DataKeeper.js") //
		.script("https://code.jquery.com/jquery-3.2.1.min.js") //
		.script("https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js") //
		.script("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js")
		.script("plugin/alertify/js/alertify.min.js") //
		.script("plugin/socket/sockjs-1.1.4.js")
		.script("plugin/socket/stomp.min.js")
		.script("plugin/bootstrap-toggle/js/bootstrap-toggle.min.js")
		.script("plugin/jquery/js/jquery-tmpl.js")
		.script("plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js")
		.script("plugin/jquery-ui-1.12.1.custom/jquery-ui.touch-punch.min.js")
		.wait(function(){
			callback();
		});
	}
	
	self.loadJS = function(href, callback) {
		$LAB.script(href).wait(function() {
			callback();
		});
	}
	
	self.loadBaseCSS = function() {
		self.loadCSS("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css") //
		.loadCSS("plugin/alertify/css/alertify.core.css") //
		.loadCSS("plugin/alertify/css/alertify.bootstrap.css") //
		.loadCSS("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css") //
		.loadCSS("https://fonts.googleapis.com/icon?family=Material+Icons")
		.loadCSS("plugin/bootstrap-toggle/css/bootstrap-toggle.min.css")
		.loadCSS("plugin/jquery-ui-1.12.1.custom/jquery-ui.min.css")
		.loadCSS("plugin/jquery-ui-1.12.1.custom/jquery-ui.structure.min.css")
		.loadCSS("plugin/jquery-ui-1.12.1.custom/jquery-ui.theme.min.css"); //
	}
	
	self.loadCSS = function(href) {
		var link = document.createElement("link");
		link.setAttribute("rel","stylesheet");
		link.setAttribute("type","text/css");
		link.setAttribute("href", href);
		document.getElementsByTagName("head")[0].appendChild(link);
		
		return self;
	}

	self.alertSuccess = function(msg) {
		alertify.success(msg);
	};

	self.alertError = function(msg) {
		alertify.error(msg);
	};

	return self;
})();
