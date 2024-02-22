package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.AddressDao;
import org.anch.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDaoImpl extends AbstractCrudDaoImpl<Integer, Address> implements AddressDao {

    public AddressDaoImpl(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
