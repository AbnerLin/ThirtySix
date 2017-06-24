package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.ItemClass;
import com.thirtySix.repository.ItemClassRepository;
import com.thirtySix.repository.ItemRepository;
import com.thirtySix.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemClassRepository itemClassRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Override
	public List<ItemClass> findAllItemClass() {
		return (List<ItemClass>) itemClassRepo.findAll();
	}

}
