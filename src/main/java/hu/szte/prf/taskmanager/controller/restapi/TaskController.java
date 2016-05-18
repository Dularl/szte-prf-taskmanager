package hu.szte.prf.taskmanager.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.szte.prf.taskmanager.entity.Task;
import hu.szte.prf.taskmanager.service.TaskService;

@RestController
@RequestMapping("/rest/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Task> list() {
		return taskService.list();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Task get(@PathVariable final Long id) {
		return taskService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody final Task task) {
		taskService.save(task);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody final Task task) {
		taskService.save(task);
	}
}
