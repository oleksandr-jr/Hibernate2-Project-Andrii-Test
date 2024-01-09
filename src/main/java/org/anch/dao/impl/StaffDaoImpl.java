package org.anch.dao.impl;

import org.anch.dao.StaffDao;
import org.anch.entity.Staff;

public class StaffDaoImpl extends AbstractCrudDaoImpl<Byte, Staff> implements StaffDao {

    public StaffDaoImpl() {
        super(Staff.class);
    }
}
