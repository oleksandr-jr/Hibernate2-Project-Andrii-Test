package org.anch.dao;

import org.anch.entity.Inventory;

import java.util.List;

public interface InventoryDao extends CrudDao<Integer, Inventory> {
    List<Inventory> findAllInventoriesByFilmId(Short filmId, Byte storeId);
}
