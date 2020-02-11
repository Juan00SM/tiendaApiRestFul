/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestfulshop.repositories;
import com.service.apirestfulshop.model.Bill;
import java.util.Date;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 *
 * @author Lidia
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{

    @Query(
            value = "SELECT * FROM bill AS b WHERE b.datePurchase LIKE %?1%",
            nativeQuery = true)
    public List<Bill> getByName(Date datePurchase);

}
