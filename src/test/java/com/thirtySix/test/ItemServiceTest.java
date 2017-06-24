package com.thirtySix.test;

import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.core.Buffer;
import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;
import com.thirtySix.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ItemServiceTest {

	@Autowired
	private Buffer buffer = null;

	@Autowired
	private ItemService itemService = null;

	@Test
	@SuppressWarnings("rawtypes")
	public void testBufferLoad() {
		Map<String, ItemClass> itemClass = buffer.getMenu();
		Assert.assertEquals(5, itemClass.size());

		Map<String, Item> item = buffer.getItems();
		Assert.assertEquals(11, item.size());

		Iterator iter = itemClass.entrySet().iterator();
		int itemSizeFromItemClass = 0;
		while (iter.hasNext()) {
			Map.Entry pair = (Map.Entry) iter.next();
			ItemClass _itemClass = (ItemClass) pair.getValue();
			itemSizeFromItemClass += _itemClass.getItemList().size();
		}
		Assert.assertEquals(item.size(), itemSizeFromItemClass);
	}

	@Test
	@Rollback
	@Transactional
	public void testSave() {
		Map<String, ItemClass> itemClass = buffer.getMenu();
		ItemClass _itemClass = itemClass.get("9ed2071e-93b1-42bb-8687-3ccdc87decb8");

		Item item = new Item();
		item.setName("測試");
		item.setImagePath("./image.jpg");
		item.setIsDisplay(1);
		item.setItemClass(_itemClass);
		item.setPrice(100);

		itemService.saveItem(item);

		int itemSize = itemService.findAllItem().size();

		Assert.assertEquals(12, itemSize);
	}

	@Test
	@Rollback
	@Transactional
	public void testDelete() {
		/** test item */
		String itemId1 = "49d643bb-c7d6-4f00-b49f-ceae68971e40";
		String itemId2 = "d4512dba-4582-4ec2-b4ac-581e3ac1e0a8";

		itemService.deleteItem(itemId1);
		itemService.deleteItem(itemId2);

		int itemSize = itemService.findAllItem().size();
		Assert.assertEquals(9, itemSize);
		
		/** test itemClass */
		String itemClassID = "9ed2071e-93b1-42bb-8687-3ccdc87decb8";
		itemService.deleteItemClass(itemClassID);

		int itemClassSize = itemService.findAllItemClass().size();
		Assert.assertEquals(4, itemClassSize);
	}
}
