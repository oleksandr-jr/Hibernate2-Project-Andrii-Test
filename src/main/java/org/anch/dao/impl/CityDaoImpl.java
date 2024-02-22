package org.anch.dao.impl;

import org.anch.dao.CityDao;
import org.anch.entity.City;
import org.hibernate.SessionFactory;

public class CityDaoImpl extends AbstractCrudDaoImpl<Integer, City> implements CityDao {
    public CityDaoImpl(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
