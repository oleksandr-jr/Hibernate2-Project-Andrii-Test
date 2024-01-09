package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.AddressDao;
import org.anch.entity.Address;
import org.hibernate.Session;

import java.util.Optional;

public class AddressDaoImpl extends AbstractCrudDaoImpl<Short, Address> implements AddressDao {

    public AddressDaoImpl() {
        super(Address.class);
    }
}
