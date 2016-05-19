package hu.szte.prf.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.ProjectDao;
import hu.szte.prf.taskmanager.entity.Project;

@Transactional
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Override
	public Project find(final Long id) {
		return projectDao.read(id);
	}

	@Override
	public List<Project> list() {
		return projectDao.getAll();
	}

	@Override
	public void save(final Project project) {
		projectDao.create(project);
	}

	@Override
	public void update(final Project project) {
		projectDao.update(project);
	}

	@Override
	public void delete(final Long id) {
		final Project project = projectDao.read(id);
		projectDao.delete(project);
	}

}
