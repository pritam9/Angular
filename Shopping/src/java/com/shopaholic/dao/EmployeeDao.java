/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import com.shopaholic.model.Employee;
import com.shopaholic.model.Product;

/**
 *
 * @author Pritam
 */
public interface EmployeeDao {
    public boolean addProduct(Product product);
    public boolean deleteProduct(String productId);
    public boolean updateProduct(Product product);
    public boolean loginEmp(String empId, String password);
    public Employee getDetails(String empId);
}
