package com.kingshuk.hibernate.cognizanttraining.models;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerAddress {
    private String City;

    private String state;

    private Integer zipCode;

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

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
