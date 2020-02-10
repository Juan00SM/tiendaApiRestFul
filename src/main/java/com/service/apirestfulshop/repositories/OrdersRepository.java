
package com.service.apirestfulshop.repositories;

import com.service.apirestfulshop.model.Orders;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author juans
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
    
    @Query(
    value="SELECT * FROM orders AS o WHERE o.status LIKE %?1%",
            nativeQuery=true)
    public List<Orders> getByStatus(String status);
    
}
