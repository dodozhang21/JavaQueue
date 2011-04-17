package net.pureessence.example.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import java.io.Serializable;
import java.util.List;

public class GenericDaoImpl<T> extends HibernateDaoSupport {
    private Class<T> type;

    public Long save(T instance) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        getHibernateTemplate().saveOrUpdate(instance);
        Serializable id = session.getIdentifier(instance);
        return (Long) id;

    }

    public void delete(T instance) {
        getHibernateTemplate().delete(instance);
    }

    public T getById(Long id) {
        return (T) getHibernateTemplate().get(type, id);
    }

    public List<T> findByExample(T example) {
        return (List<T>) getHibernateTemplate().findByExample(example);
    }

    public List<T> getAll() {
        return (List<T>) getHibernateTemplate().loadAll(type);
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}

