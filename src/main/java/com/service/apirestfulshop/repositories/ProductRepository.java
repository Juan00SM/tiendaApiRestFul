
package com.service.apirestfulshop.repositories;


import com.service.apirestfulshop.model.Product;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author carma
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{
    
    @Query(
    value="SELECT * FROM product AS p WHERE p.name LIKE %?1%",
            nativeQuery=true)
    public List<Product> getByName(String status);
}
