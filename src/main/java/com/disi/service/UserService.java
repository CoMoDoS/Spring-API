package com.disi.service;

import com.disi.dto.PatientDTO;
import com.disi.errorHandler.EntityValidationException;
import com.disi.errorHandler.ResourceNotFoundException;
import com.disi.models.Caregiver;
import com.disi.models.CustomUserDetails;
import com.disi.models.Patient;
import com.disi.models.User;
import com.disi.repository.PatientRepository;
import com.disi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return user
                .map(CustomUserDetails::new).get();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User addUser(User user){

        validateUser(user);
        findUserByEmail(user.getEmail());
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    public User getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());
    }

  /*  public User getPatient(int id){
        Optional<User> user = userRepository.findByIdAndType(id, "PATIENT");
        if(user.isPresent())
            return user.get();
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());
    }

    public List<PatientDTO> getAllPatients(){
        List<User> users =  userRepository.findAllByType("PATIENT");
        List<PatientDTO> patientDTOS = new ArrayList<>();

        for (User u: users) {
           Patient patient1 = patientRepository.findByUser(u);
           if(patient1 != null){
               Patient patient = patient1;
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
               if( patient.getCaregiver() != null )
                    patDTO.setCaregiver_id(patient.getCaregiver().getId() );
               else
                   patDTO.setCaregiver_id(0);
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
        userPatient.setPassword(passwordEncoder().encode(user.getPassword()));
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
    }*/

    private void findUserByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            List<String> errors = new ArrayList<String>();
            errors.add("This email already exists");
            throw new EntityValidationException(User.class.getSimpleName(), errors);
        }
    }

    private void validateUser(User user){
        List<String> errors = new ArrayList<String>();

        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (user.getStatus() == null || user.getStatus().equals("")) {
            errors.add("Name field should not be empty");
        }

        if (user.getPassword() == null || user.getPassword().equals("")) {
            errors.add("Password field should not be empty");
        }

        if(user.getEmail() != null) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail());
            if (!matcher.find())
                errors.add("Email field is not valid");
        }

        if (user.getEmail() == null || user.getEmail().equals("")) {
            errors.add("Email field is not valid");
        }

        if (!errors.isEmpty()) {
            throw new EntityValidationException(User.class.getSimpleName(),errors);
        }
    }

    public void createAdmin(User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

}
