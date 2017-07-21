package com.thirtySix.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtil {

	@Autowired
	private SimpMessagingTemplate messageTemplate = null;

	/**
	 * Broadcast to client to update seat map.
	 * 
	 * @param bean
	 */
	public void updateSeatMap(final Object bean) {
		this.messageTemplate.convertAndSend("/topic/updateSeatMap", bean);
	}
}
