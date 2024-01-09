package org.anch.dao.impl;

import org.anch.dao.PaymentDao;
import org.anch.entity.Payment;

public class PaymentDaoImpl extends AbstractCrudDaoImpl<Short, Payment> implements PaymentDao {
    public PaymentDaoImpl() {
        super(Payment.class);
    }
}
