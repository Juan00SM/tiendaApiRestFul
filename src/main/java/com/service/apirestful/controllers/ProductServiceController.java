/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Product;
import com.service.apirestful.services.ProductService;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    //Permite extraer todos los productos que pertenezcan a un pedido concreto
    @GetMapping("/byOrder")
    public ResponseEntity<List<Product>> getProductByOrder(@RequestParam(required = true, name = "id") Long id)
            throws RecordNotFoundException {
        List<Product> list = service.getByOrder(id);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /*
    Permit la busqueda de productos segun nombre y precio, para ello utiliza if encadenados,
    comprovando si los parametros le han sido pasados o son nulos y por tanto no busca por ellos
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductByCriteria(@RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "max") String max, @RequestParam(required = false, name = "min") String min) {
        List<Product> list = null;
        if (name == null && max == null && min == null) {
            list = new ArrayList<>();
        } else {
            if (max == null) {
                if (name == null) {
                    list = service.getByMorePrice(min);
                } else {
                    if (min == null) {
                        list = service.getByName(name);
                    } else {
                        list = service.getByNameMinPrice(name, min);
                    }
                }
            } else {
                if (name == null) {
                    if (min == null) {
                        list = service.getByLessPrices(max);
                    } else {
                        list = service.getBetweenPrices(max, min);
                    }
                } else {
                    if (min == null) {
                        list = service.getByNameMaxPrice(name, max);
                    } else {
                        list = service.getByNamePrices(name, max, min);
                    }
                }
            }
        }

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
        Product updated = service.updateProduct(myProduct);
        return new ResponseEntity<Product>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProductById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteProductById(id);
        return HttpStatus.ACCEPTED;
    }
}
