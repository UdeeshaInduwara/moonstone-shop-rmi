package lk.ijse.jewelryshoprmi.repository.impl;

import lk.ijse.jewelryshoprmi.repository.CrudRepo;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudRepoImpl<T, ID extends Serializable> implements CrudRepo<T, ID> {

    protected Session session;
    private Class<T> type;

    public CrudRepoImpl() {
        type = (Class<T>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public boolean add(T entity) throws Exception {
        try {
            session.save(entity);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public boolean delete(T entity) throws Exception {
        try {
            session.delete(entity);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public boolean update(T entity) throws Exception {
        try {
            session.update(entity);
            return true;
        }catch (HibernateException e){
            return false;
        }
    }

    @Override
    public T search(ID id) throws Exception {
        try {
            return session.get(type, id);
        }catch (HibernateException e){
            return null;
        }
    }

    @Override
    public List<T> getAll() throws Exception {
        try {
            return session.createQuery("From "+type.getName()).list();
        }catch (HibernateException e){
            return null;
        }
    }
}
