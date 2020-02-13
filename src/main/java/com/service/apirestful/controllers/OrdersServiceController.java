/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Orders;
import com.service.apirestful.services.OrdersService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juans
 */
@RestController
@RequestMapping("/orders")
public class OrdersServiceController {

    @Autowired
    OrdersService service;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> list = service.getAllOrders();

        return new ResponseEntity<List<Orders>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrdersById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Orders entity = service.getOrdersById(id);

        return new ResponseEntity<Orders>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{status}")
    public ResponseEntity<List<Orders>> getOrdersByStatus(@PathVariable("status") String status) {
        List<Orders> list = service.getOrdersByStatus(status);

        return new ResponseEntity<List<Orders>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@Valid @RequestBody Orders myOrders) {
        Orders created = service.createOrders(myOrders);
        return new ResponseEntity<Orders>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Orders> UpdateOrders(@Valid @RequestBody Orders myOrders)
            throws RecordNotFoundException {
        Orders updated = service.updateOrders(myOrders);
        return new ResponseEntity<Orders>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteOrdersById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteOrdersById(id);
        return HttpStatus.ACCEPTED;
    }

}