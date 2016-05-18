package hu.szte.prf.taskmanager.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.szte.prf.taskmanager.entity.Resource;
import hu.szte.prf.taskmanager.service.ResourceService;

@RestController
@RequestMapping("/rest/resources")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Resource> list() {
		return resourceService.list();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Resource get(@PathVariable final Long id) {
		return resourceService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody final Resource resource) {
		resourceService.save(resource);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody final Resource resource) {
		resourceService.save(resource);
	}
}
