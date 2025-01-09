package com.crm.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "mobile", nullable = false, unique = true ,length = 10)
    private String mobile;

    public Long getId() {
        return id; // Returns the value of the 'id' field
    }

    // Setter method for 'id'
    public void setId(Long id) {
        this.id = id; // Sets the 'id' field to the value passed as an argument
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for emailId
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    // Getter and Setter for mobile
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}