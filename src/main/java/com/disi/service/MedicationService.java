package com.disi.service;

import com.disi.dto.MedicationDTO;
import com.disi.errorHandler.ResourceNotFoundException;
import com.disi.models.Medication;
import com.disi.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    public Medication addMedication(Medication medication){
        medicationRepository.save(medication);
        return medication;
    }

    public Medication getMedication(int id){
        Optional<Medication> med = medicationRepository.findById(id);
        if(med.isPresent())
            return med.get();
        else
            throw new ResourceNotFoundException(Medication.class.getSimpleName());
    }

    public List<Medication> getAllMedication(){
        return medicationRepository.findAll();
    }

    public Medication editMedication(int id, Medication medication){
        Optional<Medication> medToBeEdited = medicationRepository.findById(id);
        if(medToBeEdited.isPresent()){
            Medication medToSave = medToBeEdited.get();
            medToSave.setDosage(medication.getDosage());
            medToSave.setName(medication.getName());
            medToSave.setStatus(medication.getStatus());
            medToSave.setSideEffects(medication.getSideEffects());
            medToSave = medicationRepository.save(medToSave);
            return medToSave;
        }
        else
            throw new ResourceNotFoundException(Medication.class.getSimpleName());
    }

    public int deleteMedication(int id){
        Optional<Medication> medication = medicationRepository.findById(id);
        if(medication.isPresent()){
            medicationRepository.delete(medication.get());
            return id;
        }else{
            throw new ResourceNotFoundException(Medication.class.getSimpleName());
        }
    }

}
