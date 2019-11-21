package com.disi.repository;

import com.disi.models.Caregiver;
import com.disi.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
