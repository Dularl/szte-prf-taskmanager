package hu.szte.prf.taskmanager.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.szte.prf.taskmanager.entity.User;
import hu.szte.prf.taskmanager.service.MembershipService;

@RestController
@RequestMapping("/rest/projects/{projectId}/members")
public class MembershipController {

	@Autowired
	private MembershipService membershipService;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> list(@PathVariable final Long projectId) {
		return membershipService.listMembers(projectId);
	}

	@RequestMapping(path = "/{userId}", method = RequestMethod.POST)
	public void addMember(@PathVariable final Long projectId, @PathVariable final Long userId) {
		membershipService.addMember(projectId, userId);
	}

	@RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
	public void removeMember(@PathVariable final Long projectId, @PathVariable final Long userId) {
		membershipService.removeMember(projectId, userId);
	}

}
