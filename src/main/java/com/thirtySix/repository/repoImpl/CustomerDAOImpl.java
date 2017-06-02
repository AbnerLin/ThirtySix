package com.thirtySix.repository.repoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thirtySix.model.Customer;
import com.thirtySix.repository.CustomerDAO;

//@Repository
public class CustomerDAOImpl extends GenericDAOImpl<Customer> implements
		CustomerDAO {

	public CustomerDAOImpl() {
		super(Customer.class);
	}

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
			logger.error("取得用餐中顧客發生錯誤。", e);
		}

		return result;
	}

}
