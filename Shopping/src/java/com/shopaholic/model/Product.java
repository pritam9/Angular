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
public class Product {
    private String productID,description,productName,categoryID;
    private int productPrice,quantityInStock;
    //productImage LONGBLOB, - check what to do with the image

    public Product(String productID, String description, String productName, String categoryID, int productPrice, int quantityInStock) {
        this.productID = productID;
        this.description = description;
        this.productName = productName;
        this.categoryID = categoryID;
        this.productPrice = productPrice;
        this.quantityInStock = quantityInStock;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", description=" + description + ", productName=" + productName + ", categoryID=" + categoryID + ", productPrice=" + productPrice + ", quantityInStock=" + quantityInStock + '}';
    }
 
    
}
