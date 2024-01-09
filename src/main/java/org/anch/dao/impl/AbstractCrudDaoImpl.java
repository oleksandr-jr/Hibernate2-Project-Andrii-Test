package org.anch.dao.impl;

import jakarta.persistence.NoResultException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.anch.config.SessionFactoryCreator;
import org.anch.dao.CrudDao;
import org.anch.dao.exception.NoElementPresentException;
import org.anch.entity.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCrudDaoImpl<ID, E> implements CrudDao<ID, E> {

    private final Class<E> clazz;

    @Override
    public ID save(E entity) {
        ID id;
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            id = (ID) session.save(entity);
            transaction.commit();
        }
        return id;
    }

    @Override
    public Optional<E> findById(ID id) {
        E entity = null;
        try (Session session = SessionFactoryCreator.getSessionFactory().openSession()) {
            entity = (E) session.get(clazz, (Serializable) id);
        } catch (NoResultException e) {
            throw new NoElementPresentException(clazz.getName() + " is absent in the table", e);
        }
        return Optional.of(entity);
    }

    @Override
    public void deleteById(E entity) {
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        }
    }

    @Override
    public List<E> findAll() {
        try (Session session = getCurrentSession()) {
            return session.createQuery("from " + clazz.getName()).list();
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        }
    }

    protected Session getCurrentSession() {
        return SessionFactoryCreator.getSessionFactory().openSession();
    }
}
