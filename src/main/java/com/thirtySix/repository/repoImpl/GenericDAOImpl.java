package com.thirtySix.repository.repoImpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.thirtySix.repository.GenericDAO;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements
		GenericDAO<T> {

	private Class<T> persistentClass;

	public GenericDAOImpl(Class<T> thisClass) {
		this.persistentClass = thisClass;
	}

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

	@Override
	public List<T> getAll() {
		return this.getHibernateTemplate().loadAll(this.persistentClass);
	}

	@Override
	public T get(String id) {
		return this.getHibernateTemplate().get(persistentClass, id);
	}

}
