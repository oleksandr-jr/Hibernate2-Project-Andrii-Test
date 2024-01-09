package org.anch.dao.impl;


import org.anch.dao.CountryDao;
import org.anch.entity.Country;

public class CountryDaoImpl extends AbstractCrudDaoImpl<Short, Country> implements CountryDao {
    public CountryDaoImpl() {
        super(Country.class);
    }
}
