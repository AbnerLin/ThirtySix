package com.thirtySix.auth;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thirtySix.dto.AjaxRDTO;

@Controller
@RequestMapping(value = { "/auth" })
public class AuthController {

	/**
	 * Get user detail.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/getUserPrivilege" })
	@PreAuthorize("isAuthenticated()")
	public AjaxRDTO getUserPrivilege(final HttpServletRequest request,
			final HttpServletResponse response) {
		final AjaxRDTO result = new AjaxRDTO();

		final UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		final Collection<? extends GrantedAuthority> authorities = userDetails
				.getAuthorities();

		final Integer privilege = authorities.stream().mapToInt(role -> {
			return UserRole.valueOf(role.getAuthority()).value();
		}).min().getAsInt();

		result.setStatusOK();
		result.setData(privilege);
		return result;
	}

}
