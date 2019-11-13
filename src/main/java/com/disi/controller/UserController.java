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

    @GetMapping("bucatar/get")
    public UserDTO getBucatar(@RequestParam int id){

        User user = userService.getBucatar(id);
        return new ModelMapper().map(user, UserDTO.class);
    }

    @GetMapping("/bucatar/all")
    public List<UserDTO> getAllBucatar(){

        List<User> users = userService.getAllBucatars();
        Type usersType = new TypeToken<List<UserDTO>>() {}.getType();
        return new ModelMapper().map(users, usersType);
    }

    @PostMapping("/bucatar/add")
    public UserDTO addBucatar(@RequestBody User user){
        return new ModelMapper().map(userService.addBucatar(user), UserDTO.class);
    }

    @PutMapping("/bucatar/edit")
    public UserDTO editBucatar(@RequestParam int id, @RequestBody User user){
        return new ModelMapper().map(userService.editBucatar(id,user), UserDTO.class);
    }

    @DeleteMapping("/bucatar/delete")
    public UserDTO deleteBucatar(@RequestParam int id){
        return new ModelMapper().map(userService.deleteBucatar(id), UserDTO.class);
    }

    @GetMapping("/admin")
    public void createAdmin(){

        User user = new User("admin","admin@admin.com","admin","ADMIN");
        userService.createAdmin(user);
    }
}
