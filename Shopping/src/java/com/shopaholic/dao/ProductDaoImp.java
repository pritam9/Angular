/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import com.shopaholic.model.ConfirmDetails;
import com.shopaholic.model.Product;
import com.shopaholic.model.ShippingType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pritam
 */
public class ProductDaoImp implements ProductDao {

    @Override
    public List<Product> getProducts() {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from Product";
        List<Product> productList = new ArrayList<Product>();
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("productID"), rs.getString("description"), rs.getString("productName"), rs.getString("categoryID"), rs.getInt("productPrice"), rs.getInt("quantityInStock"));
                productList.add(product);
            }

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }

    public boolean saveCart(List<Product> list, String email, String cartId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        try {
            for (Product prod : list) {
                String query = "insert into ShoppingCart values (?,?,?,?,?)";
                stmt = connection.prepareStatement(query);
                stmt.setString(1, cartId);
                stmt.setString(2, prod.getProductID());
                stmt.setString(3, email);
                stmt.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
                stmt.setInt(5, prod.getQuantityInStock());
                stmt.executeUpdate();
                String query1 = "update Product SET quantityInStock=quantityInStock-? WHERE productID=?";
                PreparedStatement pst = connection.prepareStatement(query1);
                pst.setString(2, prod.getProductID());
                pst.setInt(1, prod.getQuantityInStock());
                pst.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<ShippingType> getShippingTypes() {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from Shipping";
        List<ShippingType> types = new ArrayList<ShippingType>();
        try {
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ShippingType product = new ShippingType(rs.getString(1), rs.getFloat(2));
                types.add(product);
            }

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return types;
    }

    public boolean saveCartDetails(ConfirmDetails details, String email, String cartId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        try {
            
                String query = "insert into OrderTable values (?,?,?,?,?,?,?)";
                stmt = connection.prepareStatement(query);
                int randomPIN = (int)(Math.random()*9000)+1000;
                String orderId="O"+randomPIN;
                stmt.setString(2, cartId);
                stmt.setString(1, orderId);
                stmt.setString(3, details.getAddress());
                stmt.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
                //java.util.Date shipDate = 
                Calendar.getInstance().add(Calendar.DATE, 1);
                stmt.setDate(5, new Date(Calendar.getInstance().getTimeInMillis()));
                stmt.setString(7, "1 Day");
                stmt.setString(6,"Shipped");
                stmt.executeUpdate();
                // update other details later on..
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
