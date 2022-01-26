package com.azure.cdc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("EmployeeHistory")
public class EmployeeHistory {
    @Id
    private long id;

    private String name;

    private String designation;

    private String department;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date creationDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date updatedDate;

    private String updatedUserId;

    private String operation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public EmployeeHistory(long id, String name, String designation, String department, Date creationDate, Date updatedDate, String updatedUserId, String operation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
        this.operation = operation;
    }

    public EmployeeHistory() {
    }

    @Override
    public String toString() {
        return "EmployeeHistory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", creationDate=" + creationDate +
                ", updatedDate=" + updatedDate +
                ", updatedUserId='" + updatedUserId + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
