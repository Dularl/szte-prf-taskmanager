package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.TaskType;

public interface TaskTypeService {

	public TaskType find(final Long id);

	public List<TaskType> list();

	public void save(final TaskType taskType);

	public void update(final TaskType taskType);

}
