package hu.szte.prf.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.ResourceDao;
import hu.szte.prf.taskmanager.entity.Resource;

@Transactional
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Resource find(final Long id) {
		return resourceDao.read(id);
	}

	@Override
	public List<Resource> list() {
		return resourceDao.getAll();
	}

	@Override
	public void save(final Resource resource) {
		resourceDao.create(resource);
	}

	@Override
	public void update(final Resource resource) {
		resourceDao.update(resource);
	}

	@Override
	public void delete(final Long id) {
		final Resource resource = resourceDao.read(id);
		resourceDao.delete(resource);
	}

}
