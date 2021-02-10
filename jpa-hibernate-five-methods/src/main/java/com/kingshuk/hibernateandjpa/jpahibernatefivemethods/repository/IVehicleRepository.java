package com.kingshuk.hibernateandjpa.jpahibernatefivemethods.repository;


import com.kingshuk.hibernateandjpa.jpahibernatefivemethods.model.Vehicle;

 public interface IVehicleRepository {

     void updateUsingHibernateUpdate(Vehicle vehicle);

     void updateUsingJpaMerge(Vehicle vehicle);

     void updateUsingHibernateSaveOrUpdate(Vehicle vehicle);

     void insertUsingHibernateSave(Vehicle vehicle);

     void insertUsingJpaPersist(Vehicle vehicle);

}
