package com.thirtySix.service;

import com.thirtySix.model.UserInfo;

public interface UserService {
	
	/**
	 * Find user by user name.
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo findUser(String userName);
	
}
