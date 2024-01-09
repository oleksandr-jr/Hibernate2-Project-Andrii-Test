package org.anch.dao.impl;

import org.anch.dao.LanguageDao;
import org.anch.entity.Language;

public class LanguageDaoImpl extends AbstractCrudDaoImpl<Byte, Language> implements LanguageDao {
    public LanguageDaoImpl() {
        super(Language.class);
    }
}
