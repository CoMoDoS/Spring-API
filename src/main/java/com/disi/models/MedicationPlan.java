package com.disi.models;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medication_plan")
public class MedicationPlan {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patientId",  nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicationID",  nullable = false)
    private Medication medication;

    @Column(name = "intakeInterval")
    private String intakeInterval;

    @Column(name = "period")
    private String period;

    @Column(name = "status")
    private String status;

    public MedicationPlan(){}

    public MedicationPlan(Patient patient, Medication medication, String intakeInterval, String period, String status) {
        this.patient = patient;
        this.medication = medication;
        this.intakeInterval = intakeInterval;
        this.period = period;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getIntakeInterval() {
        return intakeInterval;
    }

    public void setIntakeInterval(String intakeInterval) {
        this.intakeInterval = intakeInterval;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
