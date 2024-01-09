package org.anch.dao.impl;

import org.anch.dao.FilmDao;
import org.anch.entity.Film;

public class FilmDaoImpl extends AbstractCrudDaoImpl<Short, Film> implements FilmDao {
    public FilmDaoImpl() {
        super(Film.class);
    }
}
