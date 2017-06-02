package com.thirtySix.repository.repoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thirtySix.model.FurnishClass;
import com.thirtySix.repository.FurnishClassDAO;

@Repository
public class FurnishDAOImpl extends GenericDAOImpl<FurnishClass> implements
		FurnishClassDAO {

	public FurnishDAOImpl() {
		super(FurnishClass.class);
	}

}
