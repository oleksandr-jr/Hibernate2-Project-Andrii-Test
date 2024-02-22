package org.anch.dao.impl;


import org.anch.dao.CountryDao;
import org.anch.entity.Country;
import org.hibernate.SessionFactory;

public class CountryDaoImpl extends AbstractCrudDaoImpl<Integer, Country> implements CountryDao {
    public CountryDaoImpl(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
