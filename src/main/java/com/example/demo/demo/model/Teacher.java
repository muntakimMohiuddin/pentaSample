package com.example.demo.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Teacher extends User {
    @NotEmpty(message = "Department Name cannot be empty")
    public String departmentName;

    public Teacher(@JsonProperty("name")String name, @JsonProperty("phone")String phone,@JsonProperty("email")String email,
    @JsonProperty("id") UUID id,@JsonProperty("departmentName")String departmentName) {
        super(name, phone, email,id);
        this.departmentName=departmentName;
    }

    public Teacher(String name, String phone,
            String email, UUID id, String departmentName, String password) {
        super(name, phone, email, id, password);
        this.departmentName = departmentName;
    }
    public String toString() {
        return super.toString() +", departmentName=" + departmentName +"]";
    }
}
