package hu.szte.prf.taskmanager.controller.restapi;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.szte.prf.taskmanager.entity.User;
import hu.szte.prf.taskmanager.service.UserService;

@RestController
@RequestMapping("/rest/users")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> list() {
		log.info("User list");
		final List<User> users = userService.list();
		return users;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public User get(@PathVariable final Long id) {
		return userService.find(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody final User user) {
		userService.save(user);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody final User user) {
		userService.save(user);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final Long id) {
		userService.delete(id);
	}

}
