package com.thirtySix.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.thirtySix.util.TimeFormatter;

@Component
public class TimeBroadcast {

	@Autowired
	private TimeFormatter timeFormatter = null;

	@Autowired
	private SimpMessagingTemplate messageTemplate = null;

//	@Scheduled(cron = "*/1 * * * * *")
	public void sendTime() {
		this.messageTemplate.convertAndSend("/topic/time",
				this.timeFormatter.getTime());
	}
}
