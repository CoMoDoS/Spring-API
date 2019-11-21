package com.disi.dto;

import com.disi.models.Caregiver;

import java.util.Date;

public class CaregiverDTO {
    private String email;
    private String password;
    private String status;
    private String type;
    private int id;
    private String name;
    private Date birthdate;
    private String gender;
    private String address;
    private int user_id;

    public CaregiverDTO(){}

    public CaregiverDTO(Caregiver caregiver){
        this.email = caregiver.getUser().getEmail();
        this.password = caregiver.getUser().getPassword();
        this.status = caregiver.getUser().getStatus();
        this.type = caregiver.getUser().getType();
        this.id = caregiver.getId();
        this.name = caregiver.getName();
        this.birthdate = caregiver.getBirthdate();
        this.gender = caregiver.getGender();
        this.address = caregiver.getAddress();
        this.user_id = caregiver.getUser().getId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
