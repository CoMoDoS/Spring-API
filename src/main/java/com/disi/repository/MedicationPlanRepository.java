package com.disi.repository;

import com.disi.models.Medication;
import com.disi.models.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {

//    Optional<MedicationPlan> findByName(String name);
    Optional<MedicationPlan> findById(int id);

}
