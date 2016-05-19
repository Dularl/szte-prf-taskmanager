package hu.szte.prf.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.TaskDao;
import hu.szte.prf.taskmanager.entity.Task;

@Transactional
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public Task find(final Long id) {
		return taskDao.read(id);
	}

	@Override
	public List<Task> list() {
		return taskDao.getAll();
	}

	@Override
	public void save(final Task task) {
		taskDao.create(task);
	}

	@Override
	public void update(final Task task) {
		taskDao.update(task);
	}

	@Override
	public void delete(final Long id) {
		final Task task = taskDao.read(id);
		taskDao.delete(task);
	}

}
