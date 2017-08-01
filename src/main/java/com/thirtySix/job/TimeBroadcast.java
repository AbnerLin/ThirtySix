package com.thirtySix.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thirtySix.webSocket.WebSocketUtil;

@Component
public class TimeBroadcast {

	@Autowired
	private WebSocketUtil ws = null;

	@Scheduled(cron = "*/1 * * * * *")
	public void sendTime() {
		this.ws.broadcastServerTime();
	}
}
