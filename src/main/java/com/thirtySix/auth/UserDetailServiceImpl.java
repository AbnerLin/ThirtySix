package com.thirtySix.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thirtySix.service.UserService;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService = null;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserDetails result = userService.findUser(userName);
		if(result == null)
			throw new UsernameNotFoundException(userName);
		else
			return result;
	}

}
