package hu.szte.prf.taskmanager.service;

import java.util.List;

import hu.szte.prf.taskmanager.entity.User;

public interface UserService {

	public User find(final Long id);

	public List<User> list();

	public void save(final User user);

	public void update(final User user);

	public void delete(final Long id);

}
