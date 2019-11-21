package com.disi.dto;

import com.disi.models.Patient;

import java.util.Date;

public class PatientDTO {

    private String email;
    private String password;
    private String status;
    private String type;

    private int id;
    private String name;
    private Date birthdate;
    private String gender;
    private String address;
    private String medicalRecord;
    private int user_id;
    private int caregiver_id;

    public PatientDTO(){}
    public PatientDTO(Patient patient){
        this.id = patient.getId();
        this.email = patient.getUser().getEmail();
        this.password = patient.getUser().getPassword();
        this.status = patient.getUser().getStatus();
        this.type = patient.getUser().getType();
        this.name = patient.getName();
        this.birthdate = patient.getBirthdate();
        this.gender = patient.getGender();
        this.address = patient.getAddress();
        this.medicalRecord = patient.getMedicalRecord();
        this.user_id = patient.getUser().getId();
        this.caregiver_id = patient.getCaregiver().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCaregiver_id() {
        return caregiver_id;
    }

    public void setCaregiver_id(int caregiver_id) {
        this.caregiver_id = caregiver_id;
    }
}
