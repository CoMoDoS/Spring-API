package com.disi.controller;

import com.disi.dto.MedicationDTO;
import com.disi.models.Medication;
import com.disi.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @PostMapping("/new")
    public MedicationDTO addMedication(@RequestBody MedicationDTO medDTO){
        Medication  medication = new ModelMapper().map(medDTO, Medication.class);
        return new ModelMapper().map(medicationService.addMedication(medication), MedicationDTO.class);
    }

    @GetMapping("/all")
    public List<MedicationDTO> getAllMedications(){
        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        List<Medication> list = medicationService.getAllMedication();
        for (Medication med : list) {
            medicationDTOS.add(new ModelMapper().map(med, MedicationDTO.class));
        }
        return  medicationDTOS;
    }

    @GetMapping()
    public Medication getMedicationById(@RequestParam int id){
        return medicationService.getMedication(id);
    }

    @PutMapping("/update")
    public Medication updateMedicationById(@RequestParam int id, @RequestBody Medication medication){
        return medicationService.editMedication(id, medication);
    }

    @DeleteMapping
    public int deleteMedicationById(@RequestParam int id){
        return medicationService.deleteMedication(id);
    }
}
