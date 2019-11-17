package com.disi.service;

import com.disi.dto.PatientDTO;
import com.disi.errorHandler.ResourceNotFoundException;
import com.disi.models.Caregiver;
import com.disi.models.Patient;
import com.disi.models.User;
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

    @Bean
    public BCryptPasswordEncoder patientPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PatientDTO getPatient(int id){
        PatientDTO patientToReturn = new PatientDTO();
        Optional<User> user = userRepository.findByIdAndType(id, "PATIENT");
        if(user.isPresent()){
            User u = user.get();
            patientToReturn.setUser_id(u.getId());
            patientToReturn.setEmail(u.getEmail());
            patientToReturn.setPassword(patientPasswordEncoder().encode(u.getPassword()));
            patientToReturn.setType(u.getType());
            patientToReturn.setStatus(u.getStatus());

            Patient patient = patientRepository.findByUser(u);
            if( patient != null ){
                patientToReturn.setId(patient.getId());
                patientToReturn.setName(patient.getName());
                patientToReturn.setBirthdate(patient.getBirthdate());
                patientToReturn.setGender(patient.getGender());
                patientToReturn.setAddress(patient.getAddress());
                patient.setMedicalRecord(patient.getMedicalRecord());
                patientToReturn.setCaregiver_id(patient.getCaregiver().getId());
            }
            return patientToReturn;
        }
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());
    }

    public List<PatientDTO> getAllPatients(){
        List<User> users =  userRepository.findAllByType("PATIENT");
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (User u: users) {
            Patient patient = patientRepository.findByUser(u);
            if(patient != null){
                PatientDTO patDTO = new PatientDTO();
                patDTO.setId(patient.getId());
                patDTO.setEmail(u.getEmail());
                patDTO.setStatus(u.getStatus());
                patDTO.setType(u.getType());
                patDTO.setName(patient.getName());
                patDTO.setBirthdate(patient.getBirthdate());
                patDTO.setGender(patient.getGender());
                patDTO.setAddress(patient.getAddress());
                patDTO.setMedicalRecord(patient.getMedicalRecord());
                patDTO.setUser_id(u.getId());
                patDTO.setCaregiver_id(patient.getCaregiver().getId() );
                patientDTOS.add(patDTO);
            }
        }
        return patientDTOS;
    }

    public PatientDTO addPatient(PatientDTO user){
//        validateUser(user);
//        findUserByEmail(user.getEmail());
        User userPatient = new User();
        userPatient.setEmail(user.getEmail());
        userPatient.setPassword(patientPasswordEncoder().encode(user.getPassword()));
        userPatient.setStatus(user.getStatus());
        userPatient.setType("PATIENT");
        User usrPat =  userRepository.save(userPatient);
//        Optional<User> usrPat = userRepository.findByEmail(user.getEmail());
        Patient patToReturn = new Patient();
        if(usrPat != null){
            Caregiver caregiver = new Caregiver(0,"auto");
            Patient patient = new Patient();
            patient.setName(user.getName());
            patient.setAddress(user.getAddress());
            patient.setBirthdate(user.getBirthdate());
            patient.setGender(user.getGender());
            patient.setMedicalRecord(user.getMedicalRecord());
            patient.setStatus(user.getStatus());
            patient.setUser(usrPat);
            patient.setCaregiver(caregiver);
            patToReturn =  patientRepository.save(patient);
        }

        PatientDTO patDTO = new PatientDTO();
        patDTO.setId(patToReturn.getId());
        patDTO.setEmail(usrPat.getEmail());
        patDTO.setStatus(usrPat.getStatus());
        patDTO.setType(usrPat.getType());
        patDTO.setName(patToReturn.getName());
        patDTO.setBirthdate(patToReturn.getBirthdate());
        patDTO.setGender(patToReturn.getGender());
        patDTO.setAddress(patToReturn.getAddress());
        patDTO.setMedicalRecord(patToReturn.getMedicalRecord());
        patDTO.setUser_id(usrPat.getId());
        patDTO.setCaregiver_id(patToReturn.getCaregiver().getId());


        return patDTO;
    }

    public User editPatient(int id, User user){
        Optional<User> userToBeEdited = userRepository.findByIdAndType(id,"PATIENT");
        if(userToBeEdited.isPresent()){
            //validateUser(user);
            //findUserByEmail(user.getEmail());
            User userToSave = userToBeEdited.get();
            userToSave.setStatus(user.getStatus());
            userToSave.setEmail(user.getEmail());
            //userToSave.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(userToSave);
            return userToSave;
        }
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());

    }

    public User deletePatient(int id){
        Optional<User> user = userRepository.findByIdAndType(id,"PATIENT");
        if(user.isPresent())
            userRepository.delete(user.get());
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());

        return user.get();
    }
}
