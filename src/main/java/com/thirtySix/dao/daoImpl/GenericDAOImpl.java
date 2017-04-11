package com.thirtySix.dao.daoImpl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.thirtySix.dao.GenericDAO;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements
		GenericDAO<T> {

	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@Override
	public Serializable insert(T po) {
		return this.getHibernateTemplate().save(po);
	}

	@Override
	public void update(T po) {
		this.getHibernateTemplate().update(po);
	}

	@Override
	public void delete(T po) {
		this.getHibernateTemplate().delete(po);
	}

}
