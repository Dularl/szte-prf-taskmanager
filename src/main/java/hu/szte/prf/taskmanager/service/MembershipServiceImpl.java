package hu.szte.prf.taskmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.MembershipDao;
import hu.szte.prf.taskmanager.dao.ProjectDao;
import hu.szte.prf.taskmanager.dao.UserDao;
import hu.szte.prf.taskmanager.entity.Membership;
import hu.szte.prf.taskmanager.entity.Project;
import hu.szte.prf.taskmanager.entity.User;

@Transactional
@Service
public class MembershipServiceImpl implements MembershipService {

	@Autowired
	private MembershipDao membershipDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> listMembers(final Long projectId) {
		return membershipDao.listMembers(projectId)
				.stream()
				.map(Membership::getUser)
				.collect(Collectors.toList());
	}

	@Override
	public void addMember(final Long projectId, final Long userId) {
		final Project project = projectDao.read(projectId);
		final User user = userDao.read(userId);
		final Membership membership = new Membership();
		membership.setProject(project);
		membership.setUser(user);
		membershipDao.create(membership);
	}

	@Override
	public void removeMember(final Long projectId, final Long userId) {
		final Membership membership = membershipDao.find(projectId, userId);
		membershipDao.delete(membership);
	}

}
