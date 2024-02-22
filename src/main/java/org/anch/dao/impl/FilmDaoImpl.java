package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.FilmDao;
import org.anch.entity.Film;
import org.hibernate.SessionFactory;

public class FilmDaoImpl extends AbstractCrudDaoImpl<Integer, Film> implements FilmDao {
    public FilmDaoImpl(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
