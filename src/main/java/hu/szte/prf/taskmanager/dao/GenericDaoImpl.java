package hu.szte.prf.taskmanager.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public PK create(T o) {
		return (PK) getSession().save(o);
	}

	@SuppressWarnings("unchecked")
	public T read(PK id) {
		return (T) getSession().get(type, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getSession().createCriteria(type).list();
	}

	public void update(T o) {
		getSession().update(o);
	}

	public void delete(T o) {
		getSession().delete(o);
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
