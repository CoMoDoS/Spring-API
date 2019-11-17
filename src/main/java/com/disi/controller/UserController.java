package com.disi.controller;

import com.disi.dto.CaregiverDTO;
import com.disi.dto.PatientDTO;
import com.disi.dto.UserDTO;
import com.disi.models.Caregiver;
import com.disi.models.User;
import com.disi.service.CaregiverService;
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

    @PutMapping("/caregiver")
    public CaregiverDTO updateCaregiver(@RequestParam int id, @RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.update(id, caregiverDTO);
    }

    @DeleteMapping("/caregiver")
    public int deleteCaregiver(@RequestParam int id){
        return caregiverService.delete(id);
    }

    @PostMapping("/inregistrare")
    public UserDTO addUser(@RequestBody User user) {
        user.setType("USER");
        return new ModelMapper().map(userService.addUser(user), UserDTO.class);
    }

//    @RequestMapping("/login")
//    public UserDTO loginWithOutSecutiry(@RequestBody User user) {
//
//        User userLogIn = userService.loginUser(user.getEmail(), user.getPassword());
//        return new ModelMapper().map(userLogIn, UserDTO.class);
//
//    }

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

    @GetMapping("patient/get")
    public UserDTO getPatient(@RequestParam int id){

        User user = userService.getPatient(id);
        return new ModelMapper().map(user, UserDTO.class);
    }

    @GetMapping("/patient/all")
    public List<PatientDTO> getAllPatients(){

        List<PatientDTO> users = userService.getAllPatients();

        return users;
    }

    @PostMapping("/patient/add")
    public PatientDTO addPatient(@RequestBody PatientDTO user){
        return userService.addPatient(user);
    }


    @GetMapping("/medic")
    public void createAdmin(){

        User user = new User("medic@medic.com","medic","MEDIC","ADMIN");
        userService.createAdmin(user);
    }
}
