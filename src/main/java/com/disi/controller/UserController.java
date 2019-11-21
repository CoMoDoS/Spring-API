package com.disi.controller;

import com.disi.dto.CaregiverDTO;
import com.disi.dto.PatientDTO;
import com.disi.dto.UserDTO;
import com.disi.models.Caregiver;
import com.disi.models.Patient;
import com.disi.models.User;
import com.disi.service.CaregiverService;
import com.disi.service.PatientService;
import com.disi.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private PatientService patientService;

    /*
           CAREGIVER CRUD
        ****************************************************************************
    */
    @PostMapping("/caregiver")
    public CaregiverDTO addCaregiver(@RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.add(caregiverDTO);
    }

    @GetMapping("/caregiver")
    public CaregiverDTO getCaregiver(@RequestParam int id){
        return caregiverService.get(id);
    }

    @GetMapping("/caregiver/all")
    public List<CaregiverDTO> getAllCaregivers(){
        return caregiverService.getAll();
    }

    @GetMapping("/caregiver/byUserId")
    public CaregiverDTO getByUserId(@RequestParam int id){
        return caregiverService.getByUserId(id);
    }

    @PutMapping("/caregiver")
    public CaregiverDTO updateCaregiver(@RequestParam int id, @RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.update(id, caregiverDTO);
    }

    @DeleteMapping("/caregiver")
    public int deleteCaregiver(@RequestParam int id){
        return caregiverService.delete(id);
    }
    /*
           USER THINGS
        ****************************************************************************
    */
    @PostMapping("/inregistrare")
    public UserDTO addUser(@RequestBody User user) {
        user.setType("USER");
        return new ModelMapper().map(userService.addUser(user), UserDTO.class);
    }

   /*    @RequestMapping("/login")
      public UserDTO loginWithOutSecutiry(@RequestBody User user) {

          User userLogIn = userService.loginUser(user.getEmail(), user.getPassword());
          return new ModelMapper().map(userLogIn, UserDTO.class);

    }*/

    //TODO
    //available only with spring security
    @RequestMapping("/currentUser")
    public UserDTO login() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelMapper().map(currentUser, UserDTO.class);

    }

    @GetMapping()
    public UserDTO getUserById(@RequestParam int id){
        User user = userService.getUserById(id);
        return new ModelMapper().map(user, UserDTO.class);
    }

    /*
        PATIENT CRUD
     ****************************************************************************
     */

    @GetMapping("/patient")
    public PatientDTO getPatient(@RequestParam int id){
        PatientDTO patient = patientService.get(id);
        return new ModelMapper().map(patient, PatientDTO.class);
    }

    @GetMapping("/patient/all")
    public List<PatientDTO> getAllPatients(){
        List<PatientDTO> users = patientService.getAll();
        return users;
    }

    @PostMapping("/patient")
    public PatientDTO addPatient(@RequestBody PatientDTO user){
        return patientService.add(user);
    }

    @PutMapping("/patient")
    public PatientDTO updatePatient(@RequestParam int id, @RequestBody PatientDTO patientDTO){
        return patientService.edit(id, patientDTO);
    }

    @DeleteMapping("/patient")
    public int deletePatient(@RequestParam int id){
        return patientService.delete(id);
    }

    @GetMapping("/patient/byCaregiver")
    public List<PatientDTO> getPatientsByCaregiverId(@RequestParam int id){
        return patientService.getByCaregiver(id);
    }

    @GetMapping("/patient/byUserId")
    public PatientDTO getPatientByUserId(@RequestParam int id){
        return patientService.getByUserId(id);
    }


    /*
        CREATE DEFAULT ADMIN DOCTOR
        ****************************************************************************
    */
    @GetMapping("/medic")
    public void createAdmin(){

        User user = new User("medic@medic.com","medic","MEDIC","ADMIN");
        userService.createAdmin(user);
    }
}
