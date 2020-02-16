/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.security;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Role;
import com.service.apirestful.model.User;
import com.service.apirestful.security.RoleRepository;
import com.service.apirestful.security.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author juans
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    
    public User findUserByEmail(String email) {
        
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User saveUser(User user) {
        
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role userRole = roleRepository.findByRole(role.getRole());
            if(userRole != null){
                roles.add(userRole);
            }else{
                roles.add(role);
            }
        }
        user.setRoles(roles);
        
        return userRepository.save(user);
    }
}
