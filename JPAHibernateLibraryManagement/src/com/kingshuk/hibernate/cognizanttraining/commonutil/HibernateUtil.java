package com.kingshuk.hibernate.cognizanttraining.commonutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistry;

/**
 * Created by co21321 on 2/24/2017.
 */
public abstract class HibernateUtil {
    //private static final SessionFactory sessionFactory = createSessionFactory();
    protected Metadata metaData=null;
    protected StandardServiceRegistry registry = null;

    public abstract SessionFactory createSessionFactory();
}
