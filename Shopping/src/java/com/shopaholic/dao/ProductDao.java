/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import com.shopaholic.model.Product;
import java.util.List;

/**
 *
 * @author Pritam
 */
public interface ProductDao {
    public List<Product> getProducts();
    
}
