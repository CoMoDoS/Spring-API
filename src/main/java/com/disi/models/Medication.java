package com.disi.models;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medication")

public class Medication {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sideEffects")
    private String sideEffects;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "status")
    private String status;

//    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
//    private List<MedicationPlan> listOfMedicationPlan;

    public Medication() {}

    public Medication(String name, String sideEffects, String dosage, String status) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.status = status;
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

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
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
