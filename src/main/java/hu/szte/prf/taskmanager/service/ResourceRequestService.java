package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.ResourceRequest;

public interface ResourceRequestService {

	public ResourceRequest find(final Long id);

	public List<ResourceRequest> list();

	public void save(final ResourceRequest resourceRequest);

	public void update(final ResourceRequest resourceRequest);

	public void delete(final Long id);

}
