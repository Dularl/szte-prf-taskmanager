package hu.szte.prf.taskmanager.dao;

import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.Project;

@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project, Long> implements ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}

}
