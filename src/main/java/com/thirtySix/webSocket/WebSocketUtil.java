package com.thirtySix.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.thirtySix.util.TimeFormatter;

@Component
public class WebSocketUtil {

	@Autowired
	private SimpMessagingTemplate messageTemplate = null;

	/**
	 * Broadcast to client for update seat map.
	 * 
	 * @param bean
	 */
	public void updateSeatMap(final Object bean) {
		this.messageTemplate.convertAndSend("/topic/updateSeatMap", bean);
	}

	/**
	 * Broadcast to client for there is new customer check in.
	 * 
	 * @param bean
	 */
	public void customerCheckIn(final Object bean) {
		this.messageTemplate.convertAndSend("/topic/customerCheckIn", bean);
	}

	/**
	 * Broadcast to client for customer check out.
	 * 
	 * @param bean
	 */
	public void customerCheckOut(final Object bean) {
		this.messageTemplate.convertAndSend("/topic/customerCheckOut", bean);
	}

	/**
	 * Broadcast Server time to all client.
	 */
	public void broadcastServerTime() {
		this.messageTemplate.convertAndSend("/topic/time",
				TimeFormatter.getInstance().getTime());
	}

	/**
	 * Broadcast to client for customer send order.
	 * 
	 * @param bean
	 */
	public void customerSendOrder(final Object bean) {
		this.messageTemplate.convertAndSend("/topic/customerSendOrder", bean);
	}

	/**
	 * Broadcast to client for meal was deliveried.
	 * 
	 * @param bean
	 */
	public void deliveryMeal(final Object bean) {
		this.messageTemplate.convertAndSend("/topic/deliveryMeal", bean);
	}
}
