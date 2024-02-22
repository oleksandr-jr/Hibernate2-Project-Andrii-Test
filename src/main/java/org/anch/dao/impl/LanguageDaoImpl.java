package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.LanguageDao;
import org.anch.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDaoImpl extends AbstractCrudDaoImpl<Integer, Language> implements LanguageDao {
    public LanguageDaoImpl(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
