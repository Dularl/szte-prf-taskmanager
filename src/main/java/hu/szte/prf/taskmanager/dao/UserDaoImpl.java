package hu.szte.prf.taskmanager.dao;

import org.springframework.stereotype.Repository;

import hu.szte.prf.taskmanager.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

}
