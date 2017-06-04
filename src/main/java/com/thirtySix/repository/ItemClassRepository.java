package com.thirtySix.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thirtySix.model.ItemClass;

public interface ItemClassRepository extends
		PagingAndSortingRepository<ItemClass, String> {

}
