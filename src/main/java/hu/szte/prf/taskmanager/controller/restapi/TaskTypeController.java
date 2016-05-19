package hu.szte.prf.taskmanager.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.szte.prf.taskmanager.entity.TaskType;
import hu.szte.prf.taskmanager.service.TaskTypeService;

@RestController
@RequestMapping("/rest/taskTypes")
public class TaskTypeController {

	@Autowired
	private TaskTypeService taskTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public List<TaskType> list() {
		return taskTypeService.list();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public TaskType get(@PathVariable final Long id) {
		return taskTypeService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody final TaskType taskType) {
		taskTypeService.save(taskType);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable final Long id, @RequestBody final TaskType taskType) {
		taskType.setId(id);
		taskTypeService.update(taskType);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Long id) {
		taskTypeService.delete(id);
	}

}
