package com.thirtySix.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.core.Buffer;
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

	@Autowired
	private Buffer buffer = null;

	@Override
	public List<ItemClass> findAllItemClass() {
		return (List<ItemClass>) this.itemClassRepo.findAll();
	}

	@Override
	public void saveItem(final Item po) {
		/** Save to DB */
		this.itemRepo.save(po);

		/** Update buffer */
		this.buffer.getMenu().get(po.getItemClass().getClassID()).getItemList()
				.add(po);
	}

	@Override
	public void saveItemClass(final ItemClass po) {
		/** Save to DB */
		this.itemClassRepo.save(po);

		/** Update buffer */
		this.buffer.getMenu().put(po.getClassID(), po);
	}

	@Override
	public List<Item> findAllItem() {
		return (List<Item>) this.itemRepo.findAll();
	}

	@Override
	public void deleteItem(final String itemID) {
		/** Save to DB */
		this.itemRepo.delete(itemID);

		/** Delete from buffer */
		this.buffer.getMenu()
				.forEach((final String id, final ItemClass itemClass) -> {
					itemClass.getItemList()
							.removeIf(item -> item.getItemID().equals(itemID));
				});
	}

	@Override
	public void deleteItemClass(final String classID) {
		/** Delete from DB */
		this.itemClassRepo.delete(classID);

		/** Update buffer */
		this.buffer.getMenu().remove(classID);
	}

}
