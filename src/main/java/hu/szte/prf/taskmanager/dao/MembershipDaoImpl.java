package hu.szte.prf.taskmanager.dao;

import hu.szte.prf.taskmanager.entity.Membership;

public class MembershipDaoImpl extends GenericDaoImpl<Membership, Long> {

	public MembershipDaoImpl() {
		super(Membership.class);
	}

}