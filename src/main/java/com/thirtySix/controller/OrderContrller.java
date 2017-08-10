package com.thirtySix.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.dto.AjaxRDTO;
import com.thirtySix.dto.DeliveryMealRDTO;
import com.thirtySix.dto.OrderQDTO;
import com.thirtySix.dto.OrderRDTO;
import com.thirtySix.model.Booking;
import com.thirtySix.model.Customer;
import com.thirtySix.model.Item;
import com.thirtySix.service.BookingService;
import com.thirtySix.service.CustomerService;
import com.thirtySix.service.ItemService;
import com.thirtySix.webSocket.WebSocketUtil;

@Controller
@RequestMapping(value = { "/order" })
public class OrderContrller {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private WebSocketUtil ws;

	/**
	 * Delivery meal.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/deliveryMeal" })
	@Secured("ROLE_INSIDE_STAFF")
	public AjaxRDTO deliveryMeal(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam("customerId") final String customerId,
			@RequestParam("bookingId") final String bookingId) {
		final AjaxRDTO result = new AjaxRDTO();

		/** Invoke service */
		final Booking booking = this.bookingService.setDeliveryMeal(customerId,
				bookingId);

		/** Broadcast to all client. */
		this.ws.deliveryMeal(new DeliveryMealRDTO(customerId, booking));

		return result;
	}

	/**
	 * @param request
	 * @param response
	 * @param orderDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/sendOrder" }, //
			consumes = "application/json", //
			produces = "application/json")
	public AjaxRDTO sendOrder(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestBody final OrderQDTO orderDTO) {
		final AjaxRDTO result = new AjaxRDTO();

		/** Check if customer exists. */
		final Map<String, Customer> diningCustomer = this.customerService
				.findDiningCustomer();
		if (diningCustomer.containsKey(orderDTO.getCustomerId())) {

			final Customer customer = diningCustomer
					.get(orderDTO.getCustomerId());

			/** Compose booking list */
			final List<Booking> bookingList = new ArrayList<Booking>();
			orderDTO.getItemList().forEach(item -> {
				final Booking booking = new Booking();

				/** Check if item exists. */
				final Item tmpItem = this.itemService
						.findItem(item.getItemId());
				if (tmpItem == null)
					return;

				booking.setCustomer(customer);
				booking.setItem(this.itemService.findItem(item.getItemId()));
				booking.setVolume(item.getAmount());

				bookingList.add(booking);
			});

			this.bookingService.saveBooking(customer.getCustomerID(),
					bookingList);

			/** Broadcast to all client. */
			this.ws.customerSendOrder(
					new OrderRDTO(customer.getCustomerID(), bookingList));

		} else {
			result.setStatusFail();
			result.setMessage("Customer not found.");
			return result;
		}

		result.setStatusOK();
		return result;
	}

}
