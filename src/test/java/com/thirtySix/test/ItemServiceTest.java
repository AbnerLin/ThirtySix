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
		final Map<String, ItemClass> itemClass = this.buffer.getMenu();
		Assert.assertEquals(5, itemClass.size());

		final long itemSize = itemClass.entrySet().stream()
				.map(map -> map.getValue())
				.flatMap(value -> value.getItemList().stream()).count();
		Assert.assertEquals(11, itemSize);

		final Iterator iter = itemClass.entrySet().iterator();
		int itemSizeFromItemClass = 0;
		while (iter.hasNext()) {
			final Map.Entry pair = (Map.Entry) iter.next();
			final ItemClass _itemClass = (ItemClass) pair.getValue();
			itemSizeFromItemClass += _itemClass.getItemList().size();
		}
		Assert.assertEquals(itemSize, itemSizeFromItemClass);
	}

	@Test
	@Rollback
	@Transactional
	public void testSave() {
		final Map<String, ItemClass> itemClass = this.buffer.getMenu();
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

		final int itemSize = this.itemService.findAllItem().size();
		Assert.assertEquals(12, itemSize);

		/** item class */
		final ItemClass newItemClass = new ItemClass();
		newItemClass.setClassName("測試");
		this.itemService.saveItemClass(newItemClass);

		final int itemClassSize = this.itemService.findAllItemClass().size();
		Assert.assertEquals(6, itemClassSize);
	}

	@Test
	@Rollback
	@Transactional
	public void testDelete() {
		/** test item */
		final String itemId1 = "49d643bb-c7d6-4f00-b49f-ceae68971e40";
		final String itemId2 = "d4512dba-4582-4ec2-b4ac-581e3ac1e0a8";

		this.itemService.deleteItem(itemId1);
		this.itemService.deleteItem(itemId2);

		final int itemSize = this.itemService.findAllItem().size();
		Assert.assertEquals(9, itemSize);

		/** test itemClass */
		final String itemClassID = "9ed2071e-93b1-42bb-8687-3ccdc87decb8";
		this.itemService.deleteItemClass(itemClassID);

		final int itemClassSize = this.itemService.findAllItemClass().size();
		Assert.assertEquals(4, itemClassSize);
	}
}
