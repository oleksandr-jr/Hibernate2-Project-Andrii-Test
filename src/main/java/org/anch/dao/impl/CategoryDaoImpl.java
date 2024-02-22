package org.anch.dao.impl;

import org.anch.dao.CategoryDao;
import org.anch.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CategoryDaoImpl extends AbstractCrudDaoImpl<Integer, Category> implements CategoryDao {

    private static final String FIND_ALL_CATEGORIES_BY_IDS_QUERY =
            "from Category where id in (:categoryIds)";

    public CategoryDaoImpl(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    @Override
    public Set<Category> findAllCategoriesByIds(Set<Integer> categoryIds) {

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
