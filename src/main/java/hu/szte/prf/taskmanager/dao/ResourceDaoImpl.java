package hu.szte.prf.taskmanager.dao;

import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.Resource;

@Repository
public class ResourceDaoImpl extends GenericDaoImpl<Resource, Long> implements ResourceDao {

	public ResourceDaoImpl() {
		super(Resource.class);
	}

}
