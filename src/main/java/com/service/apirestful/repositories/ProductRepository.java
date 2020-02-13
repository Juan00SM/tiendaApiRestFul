/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.repositories;

import com.service.apirestful.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author juans
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.name LIKE %?1%",
            nativeQuery=true)
    public List<Product> getByName(String name);
    
}
