package com.thirtySix.webSocket;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

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
		final Calendar now = Calendar.getInstance();
		final Timestamp time = new Timestamp(now.getTimeInMillis());
		this.messageTemplate.convertAndSend("/topic/time", time);
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
