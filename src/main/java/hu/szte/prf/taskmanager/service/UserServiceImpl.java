package hu.szte.prf.taskmanager.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.szte.prf.taskmanager.dao.UserDao;
import hu.szte.prf.taskmanager.entity.User;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserDao userDao;

	@Override
	public User find(Long id) {
		return userDao.read(id);
	}

	@Override
	public List<User> list() {
		log.info("user list");
		return userDao.getAll();
	}

	@Override
	public void save(User user) {
		userDao.create(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

}
