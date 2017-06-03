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
public class Employee {
    private String employeeID,firstName,lastName,phoneNumber,ssn,roletype,roleDescription;
    private Float salary;

    public Employee(String employeeID, String firstName, String lastName, String PhoneNumber, String ssn, String roletype, String roleDescription, Float salary) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = PhoneNumber;
        this.ssn = ssn;
        this.roletype = roletype;
        this.roleDescription = roleDescription;
        this.salary = salary;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.phoneNumber = PhoneNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
    
}
