package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.Resource;

public interface ResourceService {

	public Resource find(final Long id);

	public List<Resource> list();

	public void save(final Resource resource);

	public void update(final Resource resource);

}
