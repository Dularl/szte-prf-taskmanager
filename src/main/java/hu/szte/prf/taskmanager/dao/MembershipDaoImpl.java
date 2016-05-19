package hu.szte.prf.taskmanager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.Membership;

@Repository
public class MembershipDaoImpl extends GenericDaoImpl<Membership, Long> implements MembershipDao {

	public MembershipDaoImpl() {
		super(Membership.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Membership> listMembers(final Long projectId) {
		final Criteria criteria = getSession().createCriteria(getType());
		criteria.add(Restrictions.eq("project.id", projectId));
		return criteria.list();
	}

	@Override
	public Membership find(final Long projectId, final Long userId) {
		final Criteria criteria = getSession().createCriteria(getType());
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.add(Restrictions.eq("user.id", userId));
		return (Membership) criteria.uniqueResult();
	}

}
