package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.StoreDao;
import org.anch.entity.Store;
import org.hibernate.Session;

import java.util.Optional;

public class StoreDaoImpl extends AbstractCrudDaoImpl<Byte, Store> implements StoreDao {

    public StoreDaoImpl() {
        super(Store.class);
    }
}
