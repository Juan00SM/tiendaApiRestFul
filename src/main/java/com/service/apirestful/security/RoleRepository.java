/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.security;

import com.service.apirestful.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author juans
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByRole(String role);
    
}
