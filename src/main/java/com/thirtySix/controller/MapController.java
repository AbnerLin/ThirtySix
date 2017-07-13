package com.thirtySix.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.core.Buffer;
import com.thirtySix.dto.AjaxDTO;
import com.thirtySix.dto.FurnishClassDTO;
import com.thirtySix.model.FurnishClass;

@Controller
@RequestMapping(value = { "/map" })
public class MapController {

	@Autowired
	private Buffer buffer = null;

	/**
	 * Get Furnish class for design map.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getFurnishClass" })
	@PreAuthorize("isAuthenticated()")
	public AjaxDTO getFurnishClass(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxDTO result = new AjaxDTO();

		final Map<String, FurnishClass> furnishClass = this.buffer
				.getFurnishClass();

		final Map<String, FurnishClassDTO> map = new HashMap<String, FurnishClassDTO>();
		furnishClass.forEach((final String key, final FurnishClass _class) -> {
			final FurnishClassDTO dto = new FurnishClassDTO();
			dto.setClassID(_class.getClassID());

			if (_class.getName().equals(FurnishClass.CLASS.TABLE.name()))
				dto.setEnable(true);
			else
				dto.setEnable(false);
			dto.setDetail(_class);

			map.put(_class.getClassID(), dto);
		});

		result.setStatusOK();
		result.setData(map);

		return result;
	}
}
