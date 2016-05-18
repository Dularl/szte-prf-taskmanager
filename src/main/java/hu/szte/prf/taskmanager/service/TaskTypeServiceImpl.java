package hu.szte.prf.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.TaskTypeDao;
import hu.szte.prf.taskmanager.entity.TaskType;

@Transactional
@Service
public class TaskTypeServiceImpl implements TaskTypeService {

	@Autowired
	private TaskTypeDao taskTypeDao;

	@Override
	public TaskType find(final Long id) {
		return taskTypeDao.read(id);
	}

	@Override
	public List<TaskType> list() {
		return taskTypeDao.getAll();
	}

	@Override
	public void save(final TaskType taskType) {
		taskTypeDao.create(taskType);
	}

	@Override
	public void update(final TaskType taskType) {
		taskTypeDao.update(taskType);
	}

}
