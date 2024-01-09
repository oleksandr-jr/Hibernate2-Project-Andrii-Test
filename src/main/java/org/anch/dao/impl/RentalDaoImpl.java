package org.anch.dao.impl;

import org.anch.dao.RentalDao;
import org.anch.dao.impl.AbstractCrudDaoImpl;
import org.anch.entity.Inventory;
import org.anch.entity.Rental;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RentalDaoImpl extends AbstractCrudDaoImpl<Integer, Rental> implements RentalDao {

    public RentalDaoImpl() {
        super(Rental.class);
    }
}
