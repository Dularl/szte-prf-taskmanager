package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.User;

public interface MembershipService {

	List<User> listMembers(Long projectId);

	void addMember(Long projectId, Long userId);

	void removeMember(Long projectId, Long userId);

}
