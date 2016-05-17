package hu.szte.prf.taskmanager.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {

    PK create(T newInstance);

    T read(PK id);
    
    List<T> getAll();

    void update(T transientObject);

    void delete(T persistentObject);
}