package hu.szte.prf.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.ResourceRequestDao;
import hu.szte.prf.taskmanager.entity.ResourceRequest;

@Transactional
@Service
public class ResourceRequestServiceImpl implements ResourceRequestService {

	@Autowired
	private ResourceRequestDao resourceRequestDao;

	@Override
	public ResourceRequest find(final Long id) {
		return resourceRequestDao.read(id);
	}

	@Override
	public List<ResourceRequest> list() {
		return resourceRequestDao.getAll();
	}

	@Override
	public void save(final ResourceRequest resourceRequest) {
		resourceRequestDao.create(resourceRequest);
	}

	@Override
	public void update(final ResourceRequest resourceRequest) {
		resourceRequestDao.update(resourceRequest);
	}

}
