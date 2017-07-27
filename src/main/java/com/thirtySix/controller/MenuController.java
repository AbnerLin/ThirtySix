package com.thirtySix.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.AjaxRDTO;
import com.thirtySix.model.ItemClass;

@Controller
@RequestMapping(value = { "/menu" })
public class MenuController {

	@Autowired
	private Buffer buffer = null;

	/**
	 * Get Menu.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getMenu" })
	public AjaxRDTO getMenu(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxRDTO result = new AjaxRDTO();

		final Map<String, ItemClass> menu = this.buffer.getMenu();
		result.setStatusOK();
		result.setData(menu);

		return result;
	}

}
