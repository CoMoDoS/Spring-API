package com.disi.repository;

import com.disi.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    Optional<Medication> findByName(String name);
    Optional<Medication> findById(int id);
}
