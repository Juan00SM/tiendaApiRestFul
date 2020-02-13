/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author juans
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    @Query(
    value="SELECT * FROM client AS c WHERE c.name LIKE %?1%",
            nativeQuery=true)
    public List<Client> getByName(String name);
    
}
