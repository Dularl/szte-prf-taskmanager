package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.Task;

public interface TaskService {

	public Task find(final Long id);

	public List<Task> list();

	public void save(final Task task);

	public void update(final Task task);

	public void delete(final Long id);

}
