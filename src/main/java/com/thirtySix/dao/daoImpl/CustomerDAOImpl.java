package com.thirtySix.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thirtySix.dao.CustomerDAO;
import com.thirtySix.po.Customer;

@Repository
public class CustomerDAOImpl extends GenericDAOImpl<Customer> implements
		CustomerDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getDiningCustomer() {
		List<Customer> result = new ArrayList<Customer>();

		try {
			Criteria criteria = this.getSessionFactory().getCurrentSession()
					.createCriteria(Customer.class);
			criteria.add(Restrictions.isNull("checkOutTime"));
			result = criteria.list();

		} catch (Exception e) {
			logger.error("取得用餐中客戶資料發生錯誤。", e);
		}

		return result;
	}

}
