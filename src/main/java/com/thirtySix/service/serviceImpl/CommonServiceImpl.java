package com.thirtySix.service.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thirtySix.service.CommonService;

public class CommonServiceImpl<T> implements CommonService<T> {

	private CrudRepository<T, Serializable> repo;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CommonServiceImpl(CrudRepository repository) {
		this.repo = repository;
	}

	@Override
	public void save(T entity) {
		repo.save(entity);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) repo.findAll();
	}

}
