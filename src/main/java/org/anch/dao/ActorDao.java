package org.anch.dao;

import org.anch.entity.Actor;

import java.util.Set;

public interface ActorDao extends CrudDao<Short, Actor> {

    Set<Actor> findAllActorsByIds(Set<Short> ActorsId);

}
