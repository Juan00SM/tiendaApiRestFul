/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Bill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BillRepository extends JpaRepository<Bill, Long>{
    
    @Query(
            value = "SELECT * FROM bill AS b WHERE b.code LIKE %?1%",
            nativeQuery = true)
    public List<Bill> getByCode(String code);
    
    @Query(
    value="SELECT * FROM bill AS b WHERE b.totalPrice < %?1%",
            nativeQuery=true)
    public List<Bill> getLessTotalPrice(String price);
    
    @Query(
    value="SELECT * FROM bill AS b WHERE b.totalPrice > %?1%",
            nativeQuery=true)
    public List<Bill> getMoreTotalPrice(String price);
    
    @Query(
    value="SELECT * FROM bill AS b WHERE b.totalPrice < %?1% AND b.totalPrice > %?2%",
            nativeQuery=true)
    public List<Bill> getPriceTotalBetween(String maximum, String minimun);
    
}
