package org.anch.dao.impl;

import org.anch.dao.InventoryDao;
import org.anch.entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class InventoryDaoImpl extends AbstractCrudDaoImpl<Integer, Inventory> implements InventoryDao {

    private static final String FIND_ALL_BY_FILM_ID_QUERY =
            "from Inventory where film.id=:filmId and store.id=:storeId";

    public InventoryDaoImpl(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    @Override
    public List<Inventory> findAllInventoriesByFilmId(Integer filmId, Integer storeId) {

        List<Inventory> inventories;
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            inventories = session.createQuery(FIND_ALL_BY_FILM_ID_QUERY, Inventory.class)
                    .setParameter("filmId", filmId)
                    .setParameter("storeId", storeId)
                    .list();
            transaction.commit();
        }
        return inventories;
    }
}
