package com.thirtySix.service.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;
import com.thirtySix.repository.ItemClassRepository;
import com.thirtySix.repository.ItemRepository;
import com.thirtySix.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ItemClassRepository itemClassRepo;

	@Autowired
	private ItemRepository itemRepo;

	/** Menu buffer<classID, ItemClass> */
	private Map<String, ItemClass> itemMenuBuffer = new ConcurrentHashMap<String, ItemClass>();

	@PostConstruct
	public void init() {
		/** Load Menu */
		this.loadItemMenu();
	}

	@Override
	public Map<String, ItemClass> findAllItemClass() {
		return this.itemMenuBuffer;
	}

	@Override
	public void saveItem(final Item po) {
		/** Save to DB */
		this.itemRepo.save(po);

		/** Update buffer */
		this.itemMenuBuffer.get(po.getItemClass().getClassID()).getItemList()
				.add(po);
	}

	@Override
	public void saveItemClass(final ItemClass po) {
		/** Save to DB */
		this.itemClassRepo.save(po);

		/** Update buffer */
		this.itemMenuBuffer.put(po.getClassID(), po);
	}

	@Override
	public void deleteItem(final String itemID) {
		/** Save to DB */
		this.itemRepo.delete(itemID);

		/** Delete from buffer */
		this.itemMenuBuffer
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
		this.itemMenuBuffer.remove(classID);
	}

	@Override
	public Item findItem(final String itemId) {
		return this.itemMenuBuffer.entrySet().stream() //
				.map(map -> map.getValue()) //
				.flatMap(value -> value.getItemList().stream()) //
				.filter(item -> item.getItemID().equalsIgnoreCase(itemId)) //
				.findFirst() //
				.orElse(null);
	}

	/**
	 * Load ItemClass from DB.
	 */
	private void loadItemMenu() {
		final List<ItemClass> itemClassList = (List<ItemClass>) this.itemClassRepo
				.findAll();

		this.logger.info("載入菜單...");

		itemClassList.forEach(itemClass -> {
			this.itemMenuBuffer.put(itemClass.getClassID(), itemClass);
			this.logger.info("菜單種類編號：" + itemClass.getClassID() + " 種類："
					+ itemClass.getClassName());

			itemClass.getItemList().forEach(item -> {
				this.logger.info(
						"菜色編號：" + item.getItemID() + " 名稱：" + item.getName());
			});
		});

		this.logger.info("菜單載入完畢。");
	}
}
