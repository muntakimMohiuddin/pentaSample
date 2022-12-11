package com.example.demo.demo.model;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.example.demo.demo.validators.Patterns;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student extends User {
    @NotEmpty(message="Department Name cannot be empty")
    public String departmentName;
    @Pattern(regexp = Patterns.UUID_PATTERN,message = "Advisor id has to be a UUID")
    public UUID advisorId;
    public Student(@JsonProperty("name")String name, @JsonProperty("phone")String phone,@JsonProperty("email")String email,
    @JsonProperty("id") UUID id,@JsonProperty("departmentName")String departmentName) {
        super(name, phone, email,id);
        this.departmentName=departmentName;
    }
    public Student(String name, String phone,String email,UUID id,String departmentName,UUID advisorId) {
        super(name, phone, email,id);
        this.departmentName=departmentName;
        this.advisorId=advisorId;
    }

    public Student(String name, String phone,String email,UUID id,String departmentName,UUID advisorId,String password) {
        super(name, phone, email, id, password);
        this.departmentName=departmentName;
        this.advisorId=advisorId;
    }

    @Override
    public String toString() {
        return super.toString() +", departmentName=" + departmentName + ", advisorId=" + advisorId + "]";
    }

     
}
