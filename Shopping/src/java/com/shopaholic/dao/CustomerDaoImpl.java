/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import com.shopaholic.model.Address;
import com.shopaholic.model.Appointment;
import com.shopaholic.model.CreditCard;
import com.shopaholic.model.Customer;

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
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer getCustomerByEmailPassword(String email, String password) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Customer customer = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from CustomerLogin where email=? and password=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return getUserDetails(email);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customer;
    }

    @Override
    public boolean saveCustomer(Customer customer, String password) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "insert into CustomerLogin values(?,?)";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, customer.getEmail());
            stmt.setString(2, password);
            stmt.executeUpdate();
            String query1 = "insert into Customer values(?,?,?,?,?)";
            PreparedStatement stmt1 = connection.prepareStatement(query1);
            stmt1.setString(1, customer.getEmail());
            stmt1.setString(2, customer.getFirstName());
            stmt1.setString(3, customer.getLastName());
            stmt1.setString(4, customer.getPhoneNumber());
            stmt1.setString(5, customer.getCreditcard_number());
            stmt1.executeUpdate();
            stmt1.close();
            stmt.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "update Customer SET firstName=?,lastName=?,phoneNumber=?,creditcard_number=? where email=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(5, customer.getEmail());
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setString(4, customer.getCreditcard_number());
            stmt.executeUpdate();

            stmt.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private Customer getUserDetails(String email) {
        Customer customer = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from Customer where email=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNumber"), rs.getString("creditcard_number"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    @Override
    public boolean exists(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean saveCardDetails(CreditCard details, String email) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "insert into CreditCard values(?,?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, details.getCreditCardNumber());
            stmt.setString(2, details.getCardHolderName());
            stmt.setString(4, details.getValidFromDate());
            stmt.setString(3, details.getExpiryDate());
            stmt.setInt(5, details.getCvv());
            stmt.setString(6, email);
            stmt.executeUpdate();

            stmt.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean saveAddress(Address details, String email) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "insert into Address values(?,?,?,?,?)";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, details.getAddress());
            stmt.setString(2, details.getCity());
            
            stmt.setString(3, details.getStateName());
            stmt.setInt(4, details.getZipCode());
            stmt.setString(5, email);
            stmt.executeUpdate();

            stmt.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public CreditCard getCardDetails(String empoyeeId) {
        CreditCard customer = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from CreditCard where email=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, empoyeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customer = new CreditCard(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
    
    public Address getAddress(String empoyeeId) {
        Address customer = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "select * from Address where email=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, empoyeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customer = new Address(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public boolean saveFeedback(String email, String feedback, int rating) {
        Connection connection = null;
        PreparedStatement stmt = null;
        DaoConnection db_con = new DaoConnection();
        connection = db_con.getConnection();
        String query = "insert into Feedback(feedbackDate,rating,feedbackDescription,email) values(?,?,?,?)";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
            stmt.setInt(2, rating);
            stmt.setString(3, feedback);
            stmt.setString(4, email);
            
            stmt.executeUpdate();

            stmt.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

}
