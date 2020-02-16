/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Client;
import com.service.apirestful.model.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrdersRepository extends JpaRepository<Orders, Long>{
    
    @Query(
    value="SELECT * FROM orders AS o where o.date like %?1% and o.status like %?2%",nativeQuery=true)
    public List<Orders> getByCriteria(String date, String status);
    
}
