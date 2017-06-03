/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import com.shopaholic.model.Customer;
import java.util.List;

/**
 *
 * @author Pritam
 */
public interface CustomerDao {
    //public List<Product> selectProducts();
    public Customer getCustomerByEmailPassword(String email,String password);
    public boolean exists(String email);
    public boolean saveCustomer(Customer customer,String password);
    public boolean updateCustomer(Customer customer);
    
}
