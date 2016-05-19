package hu.szte.prf.taskmanager.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.szte.prf.taskmanager.entity.Project;
import hu.szte.prf.taskmanager.service.ProjectService;

@RestController
@RequestMapping("/rest/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Project> list() {
		return projectService.list();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Project get(@PathVariable final Long id) {
		return projectService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody final Project project) {
		projectService.save(project);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody final Project project) {
		projectService.save(project);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Long id) {
		projectService.delete(id);
	}

}
