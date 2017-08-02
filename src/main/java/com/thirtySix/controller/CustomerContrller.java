package com.thirtySix.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.dto.AjaxRDTO;
import com.thirtySix.dto.CheckInInfoQDTO;
import com.thirtySix.model.Customer;
import com.thirtySix.service.CustomerService;

@Controller
@RequestMapping(value = { "/customer" })
public class CustomerContrller {

	@Autowired
	private CustomerService customerService = null;

	/**
	 * Get dining customer.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getDiningCustomer" })
	public AjaxRDTO getDiningCustomer(final HttpServletRequest request,
			final HttpServletResponse response) {

		final AjaxRDTO result = new AjaxRDTO();

		final Map<String, Customer> diningCustomer = this.customerService
				.findDiningCustomer();

		result.setStatusOK();
		result.setData(diningCustomer);

		return result;
	}

	/**
	 * Customer check in.
	 * 
	 * @param request
	 * @param response
	 * @param checkInInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/checkIn" })
	public AjaxRDTO checkIn(final HttpServletRequest request,
			final HttpServletResponse response,
			@ModelAttribute final CheckInInfoQDTO checkInInfo) {

		final AjaxRDTO result = new AjaxRDTO();

		System.out.println(String.format("%s %s %d %s",
				checkInInfo.getCustomerName(), checkInInfo.getCustomerPhone(),
				checkInInfo.getPeopleCount(), checkInInfo.getFurnishId()));
		// TODO Save DB; Update Buffer; Broadcast to other client.

		return result;
	}

	/**
	 * Customer check out.
	 * 
	 * @param request
	 * @param response
	 * @param customerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/checkOut" })
	public AjaxRDTO checkOut(final HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam final String customerId) {

		final AjaxRDTO result = new AjaxRDTO();

		return result;
	}
}
