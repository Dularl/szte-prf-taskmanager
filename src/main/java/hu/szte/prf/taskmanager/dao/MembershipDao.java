package hu.szte.prf.taskmanager.dao;

import java.util.List;

import hu.szte.prf.taskmanager.entity.Membership;

public interface MembershipDao extends GenericDao<Membership, Long> {

	List<Membership> listMembers(Long projectId);

	Membership find(Long projectId, Long userId);

}
