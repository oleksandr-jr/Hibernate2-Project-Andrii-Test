package org.anch.dao.impl;

import org.anch.dao.CityDao;
import org.anch.entity.City;

public class CityDaoImpl extends AbstractCrudDaoImpl<Short, City> implements CityDao {
    public CityDaoImpl() {
        super(City.class);
    }
}
