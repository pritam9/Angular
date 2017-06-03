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
public class ShippingType {
    private String shippingType;
    private Float shippingFees;

    public ShippingType(String shippingType, Float shippingFees) {
        this.shippingType = shippingType;
        this.shippingFees = shippingFees;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Float getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(Float shippingFees) {
        this.shippingFees = shippingFees;
    }
    
}
