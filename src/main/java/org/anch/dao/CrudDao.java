package org.anch.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<ID, E> {

    ID save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void update(E entity);

    void deleteById(E entity);

}