package com.thirtySix.repository.repoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thirtySix.model.Item;
import com.thirtySix.repository.ItemDAO;

@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item> implements ItemDAO {

	public ItemDAOImpl() {
		super(Item.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Item> getShowItem() {
		List<Item> result = new ArrayList<Item>();

		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(Item.class);
			criteria.add(Restrictions.eq("isDisplay", SHOW));
			criteria.addOrder(Order.desc("name"));
			result = criteria.list();

		} catch (Exception e) {
			logger.error("取得可顯示的項目發生錯誤。", e);
		}

		return result;
	}

}
