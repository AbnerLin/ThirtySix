package com.thirtySix.repository.repoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.model.ItemClass;
import com.thirtySix.repository.ItemClassDAO;

@Repository
public class ItemClassDAOImpl extends GenericDAOImpl<ItemClass> implements
		ItemClassDAO {

	public ItemClassDAOImpl() {
		super(ItemClass.class);
	}

}
