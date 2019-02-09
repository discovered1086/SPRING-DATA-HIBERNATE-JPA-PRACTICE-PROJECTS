package com.kingshuk.hibernate.cognizanttraining.modelsrevised2;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name="address_id",unique = true)
    @SequenceGenerator(name="addressGenerator",sequenceName = "ADDRESS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressGenerator")
    private Integer addressId;

    @Column(name="address_ln1",length = 100)
    private String addressLine1;

    @Column(name="address_ln2",length = 100)
    private String addressLine2;

    @Column(nullable = false)
    private String City;

    @Column(nullable = false)
    private String state;

    @Column(name = "zip_code",nullable = false)
    private String zipCode;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
}
