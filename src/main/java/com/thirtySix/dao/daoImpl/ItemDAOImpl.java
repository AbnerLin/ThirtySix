package com.thirtySix.dao.daoImpl;

import org.springframework.stereotype.Repository;

import com.thirtySix.dao.ItemDAO;
import com.thirtySix.po.Item;

@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item> implements ItemDAO {

}
