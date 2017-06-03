/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.model;

import java.util.Date;

/**
 *
 * @author Pritam
 */
public class OrderDetails {
    private String orderID,cartID,billingAddress,orderStatus,shippingType;
    private Date orderDate,deliveryDate;

    public OrderDetails(String orderID, String cartID, String billingAddress, String orderStatus, String shippingType, Date orderDate, Date deliveryDate) {
        this.orderID = orderID;
        this.cartID = cartID;
        this.billingAddress = billingAddress;
        this.orderStatus = orderStatus;
        this.shippingType = shippingType;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
}
