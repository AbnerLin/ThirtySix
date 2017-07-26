var App = (function() {
	self = {};
	var project = "/thirtySix/";
	/** pub/sub */
	var topic = undefined;

	self.URL = (function() {
		return "http://" + window.location.host + project;
	})();
	
	self.loadBaseJS = function(callback) {
		$LAB //
		.script("https://code.jquery.com/jquery-3.2.1.min.js").wait(
				function() {
					/** pub/sub */
					topic = $({});
				}) 
		.script("js/core/common.js").wait()
		.script("js/module/Auth.js") //
		.script("js/module/DataKeeper.js") //
		.script("js/module/Map.js") //
		.script("https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js") //
		.script("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js")
		.script("plugin/alertify/js/alertify.min.js") //
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
		return self;
	};
	
	self.loadBaseCSS = function() {
		self.loadCSS("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css") //
		.loadCSS("plugin/alertify/css/alertify.core.css") //
		.loadCSS("plugin/alertify/css/alertify.bootstrap.css") //
		.loadCSS("css/toggle.css") //
		.loadCSS("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css") //
		.loadCSS("https://fonts.googleapis.com/icon?family=Material+Icons")
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
	
	self.uuid = function() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
				function(c) {
					var r = Math.random() * 16 | 0, v = c == 'x' ? r
							: (r & 0x3 | 0x8);
					return v.toString(16);
				});
	};
	
	self.subscribe = function() {
		topic.on.apply(topic, arguments);
	};
	
	self.unsubscribe = function() {
		topic.off.apply(topic, arguments);
	};
	
	self.publish = function() {
		topic.trigger.apply(topic, arguments);
	};
	
	self.showLoading = function(element) {
		var loadingStr = 
		'<div class="loader-overlay d-flex align-items-center justify-content-center">' +
			'<div class="row">' +
				'<div class="loader"></div>' +
				'<div class="d-flex align-items-center">' +
					'<div class="loadingWord">ThirtySix</div>' +
				'</div>' +
				'<div class="shiningWord loadingWord">' +
					'_' +
				'</div>' +
			'</div>' +
		'</div>';

		var div = document.createElement("div");
		$(div).attr("class", "tmpOverlay row col-12 m-0 p-0");
		$(element).wrap(div);
		$(element).addClass("blur");
		$(loadingStr).appendTo(".tmpOverlay");
		$(element).parent(".tmpOverlay").css("overflow", "hidden");
	};
	
	self.showLoadingByBtn = function(element, btn) {
		btn.prop("disabled", true);
		self.showLoading(element);
	};
	
	self.hideLoading = function(element, delay) {
		setTimeout(function(){
			element.parent(".tmpOverlay").find(".loader-overlay").remove();
			element.removeClass("blur");
			element.unwrap();
		}, delay);
	};
	
	self.hideLoadingByBtn = function(element, delay, btn) {
		btn.prop("disabled", false);
		self.hideLoading(element, delay);
	};
	
	self.ajax = function(ajaxOption) {
		var options = {
			async : true,
			url : "",
			data : {},
			type : "post",
			dataType : "json",
			timeout : 10000,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			beforeSend : null,
			success : null,
			complete : null,
			error : null,
			disableButton : null, // pass btn element.
			showLoading : null   // pass block to show loading, etc div.
		};
		$.extend(true, options, ajaxOption);
		
		return $.ajax({
			async : options.async,
			url: options.url,
			data : options.data,
			type : options.type,
			dataType : options.dataType,
			timeout : options.timeout,
			contentType : options.contentType,
			beforeSend : function(jqXHR, settings) {
				if(options.showLoading) {
					App.showLoading(options.showLoading);
				}
				if(options.disableButton) {
					options.disableButton.prop("disabled", true);
				}
				if(options.beforeSend) {
					options.beforeSend(jqXHR, settings);
				}
			},
			success : options.success,
			complete : function(jqXHR, textStatus) {
				if(options.showLoading) {
					App.hideLoading(options.showLoading);
				}
				if(options.disableButton) {
					options.disableButton.prop("disabled", false);
				}
				if (options.complete) {
	                options.complete(xhr, textStatus);
	            }
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
				if(options.error) {
					options.error(jqXHR, textStatus, errorThrown);
				}
			}
		});
	};
		
	return self;
})();
