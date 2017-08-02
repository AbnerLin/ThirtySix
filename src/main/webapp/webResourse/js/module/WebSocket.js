/**
 * Web Socket Module.
 */
var WebSocket = (function() {
	var self = {};
	var broker = "ws";
	var wsUrl = App.URL + broker;
	var stompClient = null;

	var topic = [];
	function connect() {
		try {
			var socket = new SockJS(wsUrl);
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				setTimeout(function() {
					$.each(topic, function(key, value) {
						stompClient.subscribe(value.dest, value.callback);
					});
				}, 500);
			});
		} catch (err) {
			console.log(err);
			return false;
		}
	}
	connect();

	self.subscribe = function(dest, callback) {
		try {
			topic.push({
				"dest" : dest,
				"callback" : callback
			});

			if(stompClient && stompClient.connected) {
				stompClient.subscribe(dest, callback);
			}
		} catch (err) {
			console.log(err);
			return false;
		}
	};

	self.unsubscribe = function(dest) {
		// pass
	};

	return self;
})();