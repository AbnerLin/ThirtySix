package com.thirtySix.test;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.thirtySix.model.Item;
import com.thirtySix.model.ItemClass;
import com.thirtySix.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ItemServiceTest {

	@Autowired
	private ItemService itemService = null;

	@Test
	@Rollback
	@Transactional
	public void testSave() {
		Map<String, ItemClass> itemClass = this.itemService.findAllItemClass();
		final ItemClass _itemClass = itemClass
				.get("9ed2071e-93b1-42bb-8687-3ccdc87decb8");

		/** item */
		final Item item = new Item();
		item.setName("測試");
		item.setImagePath("./image.jpg");
		item.setIsDisplay(1);
		item.setItemClass(_itemClass);
		item.setPrice(100);

		this.itemService.saveItem(item);

		long itemSize = itemClass.entrySet().stream().map(map -> map.getValue())
				.flatMap(value -> value.getItemList().stream()).count();
		Assert.assertEquals(12, itemSize);

		/** item class */
		final ItemClass newItemClass = new ItemClass();
		newItemClass.setClassName("測試");
		newItemClass.setItemList(new ArrayList<Item>());
		this.itemService.saveItemClass(newItemClass);

		int itemClassSize = this.itemService.findAllItemClass().size();
		Assert.assertEquals(7, itemClassSize);

		/** test item */
		final String itemId1 = "49d643bb-c7d6-4f00-b49f-ceae68971e40";
		final String itemId2 = "d4512dba-4582-4ec2-b4ac-581e3ac1e0a8";

		this.itemService.deleteItem(itemId1);
		this.itemService.deleteItem(itemId2);

		itemClass = this.itemService.findAllItemClass();
		itemSize = itemClass.entrySet().stream().map(map -> map.getValue())
				.flatMap(value -> value.getItemList().stream()).count();
		Assert.assertEquals(10, itemSize);

		/** test itemClass */
		final String itemClassID = "9ed2071e-93b1-42bb-8687-3ccdc87decb8";
		this.itemService.deleteItemClass(itemClassID);

		itemClassSize = this.itemService.findAllItemClass().size();
		Assert.assertEquals(6, itemClassSize);
	}

}
