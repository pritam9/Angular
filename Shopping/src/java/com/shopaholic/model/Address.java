/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.model;

/**
 *
 * @author Pritam
 */
public class Address {
    private String address,city,stateName;
    private int zipCode;

    public Address(String address, String city, String stateName, int zipCode) {
        this.address = address;
        this.city = city;
        this.stateName = stateName;
        this.zipCode = zipCode;
    }

    public Address() {
    }
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
