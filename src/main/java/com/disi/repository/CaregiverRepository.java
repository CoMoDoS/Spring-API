package com.disi.repository;

import com.disi.models.Caregiver;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Integer> {
    Caregiver findById(int id);
    Caregiver findByUserId(int id);

}
