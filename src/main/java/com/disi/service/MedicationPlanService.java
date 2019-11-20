package com.disi.service;

import com.disi.dto.MedicationDTO;
import com.disi.dto.MedicationPlanDTO;
import com.disi.dto.PatientDTO;
import com.disi.models.Medication;
import com.disi.models.MedicationPlan;
import com.disi.models.Patient;
import com.disi.models.User;
import com.disi.repository.MedicationPlanRepository;
import com.disi.repository.MedicationRepository;
import com.disi.repository.PatientRepository;
import com.disi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationPlanService {


    @Autowired
    private MedicationPlanRepository medicationPlanRepository;
    private MedicationRepository medicationRepository;
    private UserRepository userRepository;
    private PatientRepository patientRepository;

     public void addMedicationPlan(int patientID, int medicationID){

        Patient patient = patientRepository.findById(patientID);

        if(patient != null) {

                MedicationPlanDTO medicationPlanDTO = new MedicationPlanDTO();
                medicationPlanDTO.setId(1);
                medicationPlanDTO.setId_patient(patientID);
                medicationPlanDTO.setId_medication(medicationID);
                medicationPlanDTO.setTreatmentPeriod(medicationPlanDTO.getTreatmentPeriod());
                medicationPlanDTO.setIntakeIntervals(medicationPlanDTO.getIntakeIntervals());



        }

    }



}
