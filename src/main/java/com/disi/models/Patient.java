package com.disi.models;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "patient")
public class Patient {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name",  length = 200)
    private String name;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "medicalRecord", length = 500)
    private String medicalRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

    @Column(name = "status")
    private String status;

//    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//    private List<MedicationPlan> listOfMedicationPlan;


    public Patient () {}

    public Patient(String name, Date birthdate, String gender, String address, String medicalRecord, User user, Caregiver caregiver, String status) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.user = user;
        this.caregiver = caregiver;
        this.status = status;
    }

    public Patient(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public List<MedicationPlan> getListOfMedicationPlan() {
//        return listOfMedicationPlan;
//    }
//
//    public void setListOfMedicationPlan(List<MedicationPlan> listOfMedicationPlan) {
//        this.listOfMedicationPlan = listOfMedicationPlan;
//    }
}
