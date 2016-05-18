package hu.szte.prf.taskmanager.dao;

import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.TaskType;

@Repository
public class TaskTypeDaoImpl extends GenericDaoImpl<TaskType, Long> implements TaskTypeDao {

	public TaskTypeDaoImpl() {
		super(TaskType.class);
	}

}
