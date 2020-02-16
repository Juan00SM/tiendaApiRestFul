/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.security;

import com.service.apirestful.model.User;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juans
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<User> registration(){
        return new ResponseEntity<User>(new User(), new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
        
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            return new ResponseEntity<User>(new User(), new HttpHeaders(), HttpStatus.OK);
        } else {
            
            userExists = userService.saveUser(user);
            return new ResponseEntity<User>(userExists, new HttpHeaders(), HttpStatus.OK);
        }
        
    }

    
}

