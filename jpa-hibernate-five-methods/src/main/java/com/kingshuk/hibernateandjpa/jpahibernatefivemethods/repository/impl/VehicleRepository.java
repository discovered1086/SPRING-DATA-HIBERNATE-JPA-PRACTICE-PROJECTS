package com.kingshuk.hibernateandjpa.jpahibernatefivemethods.repository.impl;


import com.kingshuk.hibernateandjpa.jpahibernatefivemethods.model.Vehicle;
import com.kingshuk.hibernateandjpa.jpahibernatefivemethods.repository.IVehicleRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class VehicleRepository implements IVehicleRepository {

    @Autowired
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateUsingHibernateUpdate(Vehicle vehicle) {
        final Session session = entityManager.unwrap(Session.class);
        session.update(vehicle);
    }

    @Override
    @Transactional
    public void updateUsingJpaMerge(Vehicle vehicle) {
        entityManager.merge(vehicle);
    }

    @Override
    @Transactional
    public void updateUsingHibernateSaveOrUpdate(Vehicle vehicle) {
        final Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(vehicle);
    }

    @Override
    @Transactional
    public void insertUsingHibernateSave(Vehicle vehicle) {
        final Session session = entityManager.unwrap(Session.class);
        session.save(vehicle);
    }

    @Override
    @Transactional
    public void insertUsingJpaPersist(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }
}
