package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.Item;
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

	@Override
	public void saveItem(Item po) {
		itemRepo.save(po);
	}

	@Override
	public void saveItemClass(ItemClass po) {
		itemClassRepo.save(po);
	}

	@Override
	public List<Item> findAllItem() {
		return (List<Item>) itemRepo.findAll();
	}

	@Override
	public void deleteItem(String itemID) {
		itemRepo.delete(itemID);
	}

	@Override
	public void deleteItemClass(String classID) {
		itemClassRepo.delete(classID);
	}

}
