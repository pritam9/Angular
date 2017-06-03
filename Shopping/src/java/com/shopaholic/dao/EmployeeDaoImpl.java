/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import com.shopaholic.model.Customer;
import com.shopaholic.model.Employee;
import com.shopaholic.model.Product;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pritam
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private boolean exists(String productId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Customer customer = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from Product where productID=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, productId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean addProduct(Product product) {
        if (exists(product.getProductID())) {
            return updateProduct(product);
        } else {
            Connection connection = null;
            PreparedStatement stmt = null;
            Customer customer = null;
            DaoConnection db_con = new DaoConnection();
            connection = db_con.getConnection();
            String query = "insert into Product(productID,productName,description,productPrice,quantityInStock,categoryID) values(?,?,?,?,?,?)";
            try {
                stmt = connection.prepareStatement(query);
                stmt.setString(1, product.getProductID());
                stmt.setString(3, product.getDescription());
                stmt.setString(2, product.getProductName());
                stmt.setString(6, product.getCategoryID());
                stmt.setInt(4, product.getProductPrice());
                stmt.setInt(5, product.getQuantityInStock());
                
                stmt.executeUpdate();
                
                stmt.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    @Override
    public boolean deleteProduct(String productId) {
        Connection connection = null;
            PreparedStatement stmt = null;
            Customer customer = null;
            DaoConnection db_con = new DaoConnection();
            connection = db_con.getConnection();
            String query = "delete from Product where productID=?";
            try {
                stmt = connection.prepareStatement(query);
                stmt.setString(1, productId);
                
                stmt.executeUpdate();
                
                stmt.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        Connection connection = null;
            PreparedStatement stmt = null;
            Customer customer = null;
            DaoConnection db_con = new DaoConnection();
            connection = db_con.getConnection();
            String query = "update Product SET productName=?,description=?,productPrice=?,quantityInStock=?,categoryID=? where productID=?";
            try {
                stmt = connection.prepareStatement(query);
                stmt.setString(6, product.getProductID());
                stmt.setString(2, product.getDescription());
                stmt.setString(1, product.getProductName());
                stmt.setString(5, product.getCategoryID());
                stmt.setInt(3, product.getProductPrice());
                stmt.setInt(4, product.getQuantityInStock());
                
                stmt.executeUpdate();
                
                stmt.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
    }

    @Override
    public boolean loginEmp(String empId, String password) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Customer customer = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from EmployeeLogin where employeeID=? and password=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, empId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public Employee getDetails(String empId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Customer customer = null;
        Employee emp = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from Employee where employeeID=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, empId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                emp=new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString("email"), rs.getString("roleType"), rs.getString("roleDescription"), rs.getFloat("salary"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emp;
    }

}
