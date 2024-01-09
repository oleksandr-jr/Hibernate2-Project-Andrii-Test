package org.anch.dao.impl;

import org.anch.dao.ActorDao;
import org.anch.entity.Actor;
import org.anch.entity.Inventory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActorDaoImpl extends AbstractCrudDaoImpl<Short, Actor> implements ActorDao {

    private static final String FIND_ALL_ACTORS_BY_IDS_QUERY =
            "from Actor where id in (:actorIds)";

    public ActorDaoImpl() {
        super(Actor.class);
    }

    @Override
    public Set<Actor> findAllActorsByIds(Set<Short> actorIds) {

        Set<Actor> actors;
        try (Session session = getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            actors = new HashSet<>(
                    session.createQuery(FIND_ALL_ACTORS_BY_IDS_QUERY, Actor.class)
                            .setParameterList("actorIds", actorIds)
                            .list()
            );
            transaction.commit();
        }
        return actors;
    }
}
