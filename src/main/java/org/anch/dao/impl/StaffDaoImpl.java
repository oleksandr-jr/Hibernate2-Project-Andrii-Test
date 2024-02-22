package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.StaffDao;
import org.anch.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDaoImpl extends AbstractCrudDaoImpl<Integer, Staff> implements StaffDao {

    public StaffDaoImpl(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
