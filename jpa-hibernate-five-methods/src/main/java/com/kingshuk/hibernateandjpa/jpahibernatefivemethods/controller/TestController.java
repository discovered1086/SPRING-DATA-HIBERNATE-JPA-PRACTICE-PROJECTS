package com.kingshuk.hibernateandjpa.jpahibernatefivemethods.controller;


import com.kingshuk.hibernateandjpa.jpahibernatefivemethods.model.Vehicle;
import com.kingshuk.hibernateandjpa.jpahibernatefivemethods.repository.IVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    public static final String DATA_PROCESSED = "Data processed";
    @Autowired
    private IVehicleRepository vehicleRepository;

    @PostMapping("/hibernate-save")
    public String performHibernateInsert(@RequestBody Vehicle vehicle) {
        vehicleRepository.insertUsingHibernateSave(vehicle);
        return DATA_PROCESSED;
    }

    @PostMapping("/hibernate-save-or-update")
    public String performHibernateInsertorUpdate(@RequestBody Vehicle vehicle) {
        vehicleRepository.updateUsingHibernateSaveOrUpdate(vehicle);
        return DATA_PROCESSED;
    }

    @PostMapping("/jpa-persist")
    public String performJpaInsert(@RequestBody Vehicle vehicle) {
        vehicleRepository.insertUsingJpaPersist(vehicle);
        return DATA_PROCESSED;
    }

    @PostMapping("/jpa-merge")
    public String performJpaMerge(@RequestBody Vehicle vehicle) {
        vehicleRepository.updateUsingJpaMerge(vehicle);
        return DATA_PROCESSED;
    }

    @PostMapping("/hibernate-update")
    public String performHibernateUpdate(@RequestBody Vehicle vehicle) {
        vehicleRepository.updateUsingHibernateUpdate(vehicle);
        return DATA_PROCESSED;
    }
}
