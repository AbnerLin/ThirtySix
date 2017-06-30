package com.thirtySix.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.UserInfo;
import com.thirtySix.repository.UserRepository;
import com.thirtySix.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserInfo findUser(String userName) {
		return repository.findOne(userName);
	}

}
