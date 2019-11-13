package com.disi.controller;

import com.disi.dto.MedicationDTO;
import com.disi.models.Medication;
import com.disi.service.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
