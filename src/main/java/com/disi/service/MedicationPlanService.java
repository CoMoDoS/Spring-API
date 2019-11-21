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
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private PatientRepository patientRepository;

    public MedicationPlanDTO add(MedicationPlanDTO medicationPlanDTO) {

        MedicationPlan medicationPlan = new MedicationPlan();
        Patient patient;
        Medication medication;


        if (medicationPlanDTO.getId_patient() != 0 && medicationPlanDTO.getId_medication() != 0) {

            patient = patientRepository.findById(medicationPlanDTO.getId_patient());
            medication = medicationRepository.findById(medicationPlanDTO.getId_medication());

        }
        else {
                    patient = patientRepository.findById(1);
                    medication = medicationRepository.findById(1);
        }

                medicationPlan.setPeriod(medicationPlanDTO.getTreatmentPeriod());
                medicationPlan.setIntakeInterval(medicationPlanDTO.getIntakeIntervals());
                medicationPlan.setStatus(medicationPlanDTO.getStatus());
                medicationPlan.setPatient(patient);
                medicationPlan.setMedication(medication);

                medicationPlan = medicationPlanRepository.save(medicationPlan);
                return new MedicationPlanDTO(medicationPlan);


        }

        public List<MedicationPlanDTO> findAllByPatientId(int patientId) {

                List<MedicationPlanDTO> medicationPlans =  new ArrayList<>();

                Patient patient = patientRepository.findById(patientId);
                List<MedicationPlan> medicationPlans1 = medicationPlanRepository.findAllByPatient(patient);
                for (MedicationPlan m : medicationPlans1) {
                    medicationPlans.add(new MedicationPlanDTO(m));
                }
                return medicationPlans;
    }

        public List<MedicationPlanDTO> getAll(){

                List<MedicationPlan> medicationPlans = medicationPlanRepository.findAll();
                List<MedicationPlanDTO> medicationPlanDTOS = new ArrayList<>();
                for (MedicationPlan m : medicationPlans){
                    medicationPlanDTOS.add(new MedicationPlanDTO(m));
                }
                return medicationPlanDTOS;
    }




}
