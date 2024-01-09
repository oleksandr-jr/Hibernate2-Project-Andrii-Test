package org.anch.dao;

import org.anch.entity.Category;

import java.util.Set;

public interface CategoryDao extends CrudDao<Byte, Category> {

    Set<Category> findAllCategoriesByIds(Set<Byte> categoryIds);

}
