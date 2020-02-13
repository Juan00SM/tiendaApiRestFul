/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Orders;
import com.service.apirestful.repositories.OrdersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juans
 */
@Service
public class OrdersService {
    
    @Autowired
    OrdersRepository repository;

    public List<Orders> getAllOrders() {
        List<Orders> ordersList = repository.findAll();

        if (ordersList.size() > 0) {
            return ordersList;
        } else {
            return new ArrayList<Orders>();
        }
    }

    public Orders getOrdersById(Long id) throws RecordNotFoundException {
        Optional<Orders> orders = repository.findById(id);

        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new RecordNotFoundException("No orders record exist for given id", id);
        }
    }

    public Orders createOrders(Orders entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Orders updateOrders(Orders entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Orders> orders = repository.findById(entity.getId());

            if (orders.isPresent()) {
                Orders newEntity = orders.get();
                //newEntity.setId(entity.getId());
                newEntity.setClient(entity.getClient());
                newEntity.setStatus(entity.getStatus());
                newEntity.setCreationDate(entity.getCreationDate());
                newEntity.setBill(entity.getBill());
                newEntity.setProducts(entity.getProducts());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Orders not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of orders given", 0l);
        }
    }

    public void deleteOrdersById(Long id) throws RecordNotFoundException {
        Optional<Orders> orders = repository.findById(id);

        if (orders.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No orders record exist for given id", id);
        }
    }

    public List<Orders> getOrdersByStatus(String status) {
        List<Orders> ordersList = repository.getByStatus(status);

        if (ordersList.size() > 0) {
            return ordersList;
        } else {
            return new ArrayList<Orders>();
        }
    }
    
}
