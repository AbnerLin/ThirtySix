package com.thirtySix.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thirtySix.Core.Buffer;
import com.thirtySix.Core.DBManager;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.CustomerDTO;
import com.thirtySix.po.Customer;
import com.thirtySix.util.ObjectConverter;

@Controller
public class IndexController {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private DBManager dbManager = null;

	@Autowired
	private ObjectConverter objConverter = null;

	@RequestMapping(value = { "/1" })
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("test");
	}

	/**
	 * 取得用餐中的顧客
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getDiningCustomer" })
	public AjaxDTO getDiningCustomer(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO rdto ? jsonResult ?
		AjaxDTO result = new AjaxDTO();

		Map<String, Customer> diningCustomer = buffer.getDiningCustomer();

		result.setStatusOK();
		result.setData(diningCustomer);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = { "/customerCheckIn" })
	public AjaxDTO customerCheckIn(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute CustomerDTO customerDTO) {
		AjaxDTO result = new AjaxDTO();

		/** 設定進場時間 */
		Date now = new Date();
		Timestamp time = new Timestamp(now.getTime());
		customerDTO.setCheckInTime(time);

		System.out.println(customerDTO.toString());

		/** 儲存資料庫 */
		Customer po = objConverter.customerDTOtoPO(customerDTO);
		dbManager.insertCustomer(po);

		/** 更新buffer */
		buffer.getDiningCustomer().put(po.getCustomerID(), po);

		/** push socket to every client */
		// TODO

		result.setStatusOK();
		result.setData(po);
		return result;
	}
}
