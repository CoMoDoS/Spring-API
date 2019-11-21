package com.disi.dto;

import com.disi.models.Medication;
import com.disi.models.MedicationPlan;

import java.util.List;

public class MedicationPlanDTO {

    private int id;
    //private List<Medication> medPlan;
    private String treatmentPeriod;
    private String intakeIntervals;
    private int id_patient;
    private int id_medication;
    private String status;

    public MedicationPlanDTO (MedicationPlan medicationPlan) {

        this.treatmentPeriod = medicationPlan.getPeriod();
        this.intakeIntervals = medicationPlan.getIntakeInterval();
        this.status = medicationPlan.getStatus();
        this.id_patient = medicationPlan.getPatient().getId();
        this.id_medication = medicationPlan.getMedication().getId();

    }

    public MedicationPlanDTO () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public List<Medication> getMedPlan() {
        return medPlan;
    }

    public void setMedPlan(List<Medication> medPlan) {
        this.medPlan = medPlan;
    }
*/
    public String getTreatmentPeriod() {
        return treatmentPeriod;
    }

    public void setTreatmentPeriod(String treatmentPeriod) {
        this.treatmentPeriod = treatmentPeriod;
    }

    public String getIntakeIntervals() {
        return intakeIntervals;
    }

    public void setIntakeIntervals(String intakeIntervals) {
        this.intakeIntervals = intakeIntervals;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_medication() {
        return id_medication;
    }

    public void setId_medication(int id_medication) {
        this.id_medication = id_medication;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
