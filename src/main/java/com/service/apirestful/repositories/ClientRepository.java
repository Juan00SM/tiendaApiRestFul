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

public interface ClientRepository extends JpaRepository<Client, Long>{
    
    @Query(
    value="SELECT * FROM client AS c where c.name like %?1% and c.age like %?2% and c.phone like %?3%",nativeQuery=true)
    public List<Client> getByCriteria(String name, String age, String phone);
    
}
