package com.financemanagement.domaindevelopment.transactionmodeling.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class TransactionTestRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public <T> void saveEntity(T entity){
        logger.info("Entity being saved: "+ entity);
        entityManager.persist(entity);
    }

    @Transactional
    public <T> void mergeEntity(T entity){
        logger.info("Entity being merged: "+ entity);
        entityManager.merge(entity);
    }
}
