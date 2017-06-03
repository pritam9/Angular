/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pritam
 */
public class DaoConnection {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// Database URL
	//private static final String DB_URL = "jdbc:mysql://localhost:3306/programming";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/OnlineShopping";
	// Database credentials
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public String getDriver() {
		
		return JDBC_DRIVER;
	}

	public String getDBURL() {
		
		return DB_URL;
	}

	public String getUserName() {
		
		return USERNAME;
	}

	public String getPassword() {
		
		return PASSWORD;
	}
	public Connection getConnection(){
		Connection connection = null;
		try {
			// Register JDBC driver
			Class.forName(this.getDriver());

			// Open a connection
			connection = DriverManager.getConnection(
							this.getDBURL(), 
							this.getUserName(), 
							this.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
}
