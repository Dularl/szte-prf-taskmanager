package hu.szte.prf.taskmanager.dao;

import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.ResourceRequest;

@Repository
public class ResourceRequestDaoImpl extends GenericDaoImpl<ResourceRequest, Long> implements ResourceRequestDao {

	public ResourceRequestDaoImpl() {
		super(ResourceRequest.class);
	}

}
