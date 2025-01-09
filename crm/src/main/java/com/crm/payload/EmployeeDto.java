package com.crm.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.Date;


@Builder
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 3 , message = "Atleast 3 chars required")
    private String name;
    @Email
    private String emailId;
    @Size(min=10 , max =10 , message ="should be 10 digits")
    private String mobile;
    private Date date;

    public EmployeeDto(Long id, String name, String emailId, String mobile , Date date) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.mobile = mobile;
        this.date= date;
    }

    public EmployeeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
