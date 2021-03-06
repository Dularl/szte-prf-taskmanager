package hu.szte.prf.taskmanager.dao;

import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.Task;

@Repository
public class TaskDaoImpl extends GenericDaoImpl<Task, Long> implements TaskDao {

	public TaskDaoImpl() {
		super(Task.class);
	}

}
