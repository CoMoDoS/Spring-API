package com.disi.service;

import com.disi.dto.PatientDTO;
import com.disi.errorHandler.ResourceNotFoundException;
import com.disi.models.Caregiver;
import com.disi.models.Patient;
import com.disi.models.User;
import com.disi.repository.CaregiverRepository;
import com.disi.repository.PatientRepository;
import com.disi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Bean
    public BCryptPasswordEncoder patientPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PatientDTO get(int id){
        Patient patient = patientRepository.findById(id);
        if(patient != null )
            return new PatientDTO(patient);
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());
    }

    public List<PatientDTO> getAll(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient p : patients){
            patientDTOS.add(new PatientDTO(p));
        }
        return patientDTOS;
    }

    public PatientDTO add(PatientDTO patientDTO){
        User user = new User();
        user.setEmail(patientDTO.getEmail());
        user.setPassword(patientPasswordEncoder().encode(patientDTO.getPassword()));
        user.setStatus(patientDTO.getStatus());
        user.setType("PATIENT");
        user =  userRepository.save(user);

        Patient patient = new Patient();
        Caregiver caregiver;
        if(user != null){
            if(patientDTO.getCaregiver_id() != 0)
                caregiver = caregiverRepository.findById(patientDTO.getCaregiver_id());
            else
                caregiver = caregiverRepository.findById(1);
            patient.setName(patientDTO.getName());
            patient.setAddress(patientDTO.getAddress());
            patient.setBirthdate(patientDTO.getBirthdate());
            patient.setGender(patientDTO.getGender());
            patient.setMedicalRecord(patientDTO.getMedicalRecord());
            patient.setStatus(patientDTO.getStatus());
            patient.setUser(user);
            patient.setCaregiver(caregiver);
            patient =  patientRepository.save(patient);
            return new PatientDTO(patient);
        }
        else return null;
    }

    public PatientDTO edit(int id, PatientDTO patientDTO){
        Patient patient = patientRepository.findById(id);
        if( patient != null ) {
            patient.setName(patientDTO.getName());
            patient.setBirthdate(patientDTO.getBirthdate());
            patient.setGender(patientDTO.getGender());
            patient.setAddress(patientDTO.getAddress());
            patient.setMedicalRecord(patientDTO.getMedicalRecord());
            Optional<User> user = userRepository.findById(patientDTO.getUser_id());
            if(user.isPresent())
                patient.setUser(user.get());
            patient.setCaregiver(caregiverRepository.findById(patientDTO.getCaregiver_id()));
            patient = patientRepository.save(patient);

            return new PatientDTO(patient);
        }
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());

    }

    public int delete(int id){
        Patient patient = patientRepository.findById(id);
        if(patient != null ){
            patientRepository.delete(patient);
            return id;
        }

        else
            throw new ResourceNotFoundException(User.class.getSimpleName());
    }

    public List<PatientDTO> getByCaregiver(int caregiverId){
        List<PatientDTO> response = new ArrayList<>();
        Caregiver caregiver = caregiverRepository.findById(caregiverId);
        List<Patient> patients = patientRepository.findAllByCaregiver(caregiver);
        for(Patient p : patients){
            response.add(new PatientDTO(p));
        }
        return response;
    }

    public PatientDTO getByUserId(int id){
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            User user = u.get();
            Patient patient = patientRepository.findByUser(user);
            return new PatientDTO(patient);
        }else{
            throw new ResourceNotFoundException(Patient.class.getSimpleName());
        }
    }

}
