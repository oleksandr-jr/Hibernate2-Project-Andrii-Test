package org.anch.dao.impl;

import org.anch.dao.CategoryDao;
import org.anch.entity.Actor;
import org.anch.entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CategoryDaoImpl extends AbstractCrudDaoImpl<Byte, Category> implements CategoryDao {

    private static final String FIND_ALL_CATEGORIES_BY_IDS_QUERY =
            "from Category where id in (:categoryIds)";

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public Set<Category> findAllCategoriesByIds(Set<Byte> categoryIds) {

        Set<Category> categories;
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            categories = new HashSet<>(
                    session.createQuery(FIND_ALL_CATEGORIES_BY_IDS_QUERY, Category.class)
                            .setParameterList("categoryIds", categoryIds)
                            .list()
            );
            transaction.commit();
        }
        return categories;
    }
}
