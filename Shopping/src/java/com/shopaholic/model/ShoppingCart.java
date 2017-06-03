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
public class ShoppingCart {
    private String cartID,productID,email ;
    private Date dateAdded;
    private int quantity;

    public ShoppingCart(String cartID, String productID, String email, Date dateAdded, int quantity) {
        this.cartID = cartID;
        this.productID = productID;
        this.email = email;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
