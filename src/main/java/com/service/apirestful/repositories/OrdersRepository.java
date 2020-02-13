/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author juans
 */
public interface OrdersRepository extends JpaRepository<Orders, Long>{
    
    @Query(
    value="SELECT * FROM orders AS o WHERE o.status LIKE %?1%",
            nativeQuery=true)
    public List<Orders> getByStatus(String status);
    
}
