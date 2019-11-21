package com.disi.repository;

import com.disi.models.Medication;
import com.disi.models.MedicationPlan;
import com.disi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {

    Optional<MedicationPlan> findById(int id);
    List<MedicationPlan> findAllByPatient(Patient patient);

}
