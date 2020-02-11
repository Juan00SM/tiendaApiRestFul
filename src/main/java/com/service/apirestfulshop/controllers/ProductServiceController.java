/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestfulshop.controllers;

import com.service.apirestfulshop.exceptions.RecordNotFoundException;
import com.service.apirestfulshop.model.Product;
import com.service.apirestfulshop.services.ProductService;
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
 * @author carma
 */

@RestController
@RequestMapping("/product")
public class ProductServiceController {
    @Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> list = service.getAllProduct();

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Product entity = service.getProductById(id);

        return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable("name") String name) {
        List<Product> list = service.getProductByName(name);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product myProduct) {
        Product created = service.createProduct(myProduct);
        return new ResponseEntity<Product>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> UpdateProduct(@Valid @RequestBody Product myProduct)
            throws RecordNotFoundException {
        Product updated = service.createProduct(myProduct);
        return new ResponseEntity<Product>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteProductById(id);
        return HttpStatus.ACCEPTED;
    }
}
