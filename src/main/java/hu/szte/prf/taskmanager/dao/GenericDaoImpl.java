package hu.szte.prf.taskmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private final Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;

	public GenericDaoImpl(final Class<T> type) {
		this.type = type;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK create(final T o) {
		return (PK) getSession().save(o);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T read(final PK id) {
		return (T) getSession().get(type, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getSession().createCriteria(type).list();
	}

	@Override
	public void update(final T o) {
		getSession().update(o);
	}

	@Override
	public void delete(final T o) {
		getSession().delete(o);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Class<T> getType() {
		return type;
	}

}
