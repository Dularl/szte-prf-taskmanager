package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.Project;

public interface ProjectService {

	public Project find(final Long id);

	public List<Project> list();

	public void save(final Project project);

	public void update(final Project project);

	public void delete(final Long id);

}
