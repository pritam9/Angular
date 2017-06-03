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
public class Department {
    private String departmentID,deptartmentName,description;

    public Department(String departmentID, String deptartmentName, String description) {
        this.departmentID = departmentID;
        this.deptartmentName = deptartmentName;
        this.description = description;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDeptartmentName() {
        return deptartmentName;
    }

    public void setDeptartmentName(String deptartmentName) {
        this.deptartmentName = deptartmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
