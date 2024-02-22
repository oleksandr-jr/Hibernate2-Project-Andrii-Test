package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.StoreDao;
import org.anch.entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class StoreDaoImpl extends AbstractCrudDaoImpl<Integer, Store> implements StoreDao {

    public StoreDaoImpl(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
