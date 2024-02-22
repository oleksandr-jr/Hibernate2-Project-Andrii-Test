package org.anch.dao.impl;

import org.anch.config.SessionFactoryCreator;
import org.anch.dao.PaymentDao;
import org.anch.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDaoImpl extends AbstractCrudDaoImpl<Integer, Payment> implements PaymentDao {
    public PaymentDaoImpl(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
