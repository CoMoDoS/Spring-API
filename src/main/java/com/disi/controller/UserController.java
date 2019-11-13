package com.disi.controller;

import com.disi.dto.UserDTO;
import com.disi.models.User;
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

    @GetMapping("patient/get")
    public UserDTO getPatient(@RequestParam int id){

        User user = userService.getPatient(id);
        return new ModelMapper().map(user, UserDTO.class);
    }

    @GetMapping("/patient/all")
    public List<UserDTO> getAllPatients(){

        List<User> users = userService.getAllPatients();
        Type usersType = new TypeToken<List<UserDTO>>() {}.getType();
        return new ModelMapper().map(users, usersType);
    }

    @PostMapping("/patient/add")
    public UserDTO addPatient(@RequestBody User user){
        return new ModelMapper().map(userService.addPatient(user), UserDTO.class);
    }

    @PutMapping("/bucatar/edit")
    public UserDTO editPatient(@RequestParam int id, @RequestBody User user){
        return new ModelMapper().map(userService.editPatient(id,user), UserDTO.class);
    }

    @DeleteMapping("/bucatar/delete")
    public UserDTO deletePatient(@RequestParam int id){
        return new ModelMapper().map(userService.deletePatient(id), UserDTO.class);
    }

    @GetMapping("/medic")
    public void createAdmin(){

        User user = new User("medic","medic@medic.com","MEDIC","ADMIN");
        userService.createAdmin(user);
    }
}
