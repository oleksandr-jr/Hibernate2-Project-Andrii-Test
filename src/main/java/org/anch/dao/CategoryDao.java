package org.anch.dao;

import org.anch.entity.Category;

import java.util.Set;

public interface CategoryDao extends CrudDao<Integer, Category> {

    Set<Category> findAllCategoriesByIds(Set<Integer> categoryIds);

}
