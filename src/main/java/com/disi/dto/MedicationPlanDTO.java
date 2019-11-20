package com.disi.dto;

import com.disi.models.Medication;

import java.util.List;

public class MedicationPlanDTO {

    private int id;
    //private List<Medication> medPlan;
    private int treatmentPeriod;
    private int intakeIntervals;
    private int id_patient;
    private int id_medication;

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
    public int getTreatmentPeriod() {
        return treatmentPeriod;
    }

    public void setTreatmentPeriod(int treatmentPeriod) {
        this.treatmentPeriod = treatmentPeriod;
    }

    public int getIntakeIntervals() {
        return intakeIntervals;
    }

    public void setIntakeIntervals(int intakeIntervals) {
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
}
