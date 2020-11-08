package com.bakatkin.crud.controller;

import com.bakatkin.crud.model.User;
import com.bakatkin.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/usersAll/")
    public ResponseEntity<List<User>> read() {
        List<User> userList = userService.allUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable long id){
        return userService.getById(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser/")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        userService.changeUser(user.getId(), user.getName(), user.getSurname(), user.getAddress(), user.getPassword(), user.getRoles());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/createUser/")
    public ResponseEntity<?> createUser(@RequestBody User user){

        /*User u = new User(user.getName(), user.getSurname(), user.getAddress(), user.getPassword(), user.getRoles());*/
        /*user.getRoles().stream().forEach(System.out::println);
        System.out.println(user.getName());
        System.out.println(user.getSurname());
        System.out.println(user.getAddress());
        System.out.println(user.getPassword());*/
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}