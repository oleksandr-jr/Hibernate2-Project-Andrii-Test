package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.CustomerDao;
import org.anch.entity.Address;
import org.anch.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class CustomerDaoImpl extends AbstractCrudDaoImpl<Short, Customer> implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }
}
