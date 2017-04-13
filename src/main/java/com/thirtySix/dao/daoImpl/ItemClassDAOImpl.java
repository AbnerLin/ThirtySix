package com.thirtySix.dao.daoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.dao.ItemClassDAO;
import com.thirtySix.po.ItemClass;

@Repository
public class ItemClassDAOImpl extends GenericDAOImpl<ItemClass> implements
		ItemClassDAO {

	public ItemClassDAOImpl() {
		super(ItemClass.class);
	}

}
