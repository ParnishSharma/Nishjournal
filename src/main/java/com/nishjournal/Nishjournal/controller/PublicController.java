package com.nishjournal.Nishjournal.controller;

import com.nishjournal.Nishjournal.entry.User;
import com.nishjournal.Nishjournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
 public String health(){
     return "OK";
 }
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
