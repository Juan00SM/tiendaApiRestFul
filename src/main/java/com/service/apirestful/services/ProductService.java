/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Product;
import com.service.apirestful.repositories.ProductRepository;
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
public class ProductService {
    
    @Autowired
    ProductRepository repository;

    public List<Product> getAllProduct() {
        List<Product> ProductList = repository.findAll();

        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }

    public Product getProductById(Long id) throws RecordNotFoundException {
        Optional<Product> Product = repository.findById(id);

        if (Product.isPresent()) {
            return Product.get();
        } else {
            throw new RecordNotFoundException("No Product record exist for given id", id);
        }
    }

    public Product createProduct(Product entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Product updateProduct(Product entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Product> Product = repository.findById(entity.getId());

            if (Product.isPresent()) {
                Product newEntity = Product.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setDescription(entity.getDescription());
                newEntity.setPrice(entity.getPrice());
                newEntity.setOrders(entity.getOrders());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Product not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of Product given", 0l);
        }
    }

    public void deleteProductById(Long id) throws RecordNotFoundException {
        Optional<Product> Product = repository.findById(id);

        if (Product.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Product record exist for given id", id);
        }
    }

    public List<Product> getProductByName(String name) {
        List<Product> ProductList = repository.getByName(name);

        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    
}
