/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.security;

import com.service.apirestful.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author juans
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    
    public User findByEmail(String email);
    
    public User findByUsername(String userName);
}
