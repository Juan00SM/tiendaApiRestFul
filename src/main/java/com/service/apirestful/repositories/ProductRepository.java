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
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.price < %?1%",
            nativeQuery=true)
    public List<Product> getLessPrice(String price);
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.price > %?1%",
            nativeQuery=true)
    public List<Product> getMorePrice(String price);
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.price < %?1% AND p.price > %?2%",
            nativeQuery=true)
    public List<Product> getPriceBetween(String maximum, String minimun);
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.name LIKE %?1% AND p.price < %?2% AND p.price > %?3%",
            nativeQuery=true)
    public List<Product> getByNamePrices(String description,String maximum, String minimun);
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.name LIKE %?1% AND p.price < %?2%",
            nativeQuery=true)
    public List<Product> getByNameMaxPrice(String description,String maximum);
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.name LIKE %?1% AND p.price > %?2%",
            nativeQuery=true)
    public List<Product> getByNameMinPrice(String description,String minimun);
}
