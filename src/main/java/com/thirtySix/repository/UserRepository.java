package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.UserInfo;

public interface UserRepository
		extends PagingAndSortingRepository<UserInfo, String> {

}
