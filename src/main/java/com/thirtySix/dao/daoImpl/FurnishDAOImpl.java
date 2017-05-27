package com.thirtySix.dao.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thirtySix.dao.FurnishClassDAO;
import com.thirtySix.po.FurnishClass;

@Repository
public class FurnishDAOImpl extends GenericDAOImpl<FurnishClass> implements
		FurnishClassDAO {

	public FurnishDAOImpl() {
		super(FurnishClass.class);
	}

}
