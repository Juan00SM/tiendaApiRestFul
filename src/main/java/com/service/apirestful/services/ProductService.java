/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Orders;
import com.service.apirestful.model.Product;
import com.service.apirestful.repositories.OrdersRepository;
import com.service.apirestful.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juans
 */
@Service
public class ProductService {
    
    @Autowired
    ProductRepository repositoryProduct;
    @Autowired
    OrdersRepository repositoryOrder;

    public List<Product> getAllProduct() {
        List<Product> ProductList = repositoryProduct.findAll();

        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }

    public Product getProductById(Long id) throws RecordNotFoundException {
        Optional<Product> Product = repositoryProduct.findById(id);

        if (Product.isPresent()) {
            return Product.get();
        } else {
            throw new RecordNotFoundException("No Product record exist for given id", id);
        }
    }

    public Product createProduct(Product entity) {
        Set<Orders> orders=new HashSet<>();
        Orders o;
        
        for (Orders order : entity.getOrders()) {
            o= repositoryOrder.findById(order.getId()).get();
            if(o==null){
                orders.add(order);
            }else{
                orders.add(o);
            }
        }
        entity.setOrders(orders);
        entity = repositoryProduct.save(entity);
        return entity;
    }

    public Product updateProduct(Product entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Product> Product = repositoryProduct.findById(entity.getId());

            if (Product.isPresent()) {
                Product newEntity = Product.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setDescription(entity.getDescription());
                newEntity.setPrice(entity.getPrice());
                newEntity.setOrders(entity.getOrders());

                newEntity = repositoryProduct.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Product not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of Product given", 0l);
        }
    }

    public void deleteProductById(Long id) throws RecordNotFoundException {
        Optional<Product> Product = repositoryProduct.findById(id);

        if (Product.isPresent()) {
            repositoryProduct.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Product record exist for given id", id);
        }
    }

    public List<Product> getByName(String name) {
        List<Product> ProductList = repositoryProduct.getByName(name);

        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    //Extrae los productos que coincidan con un nombre dado y que se encuentren en un margen de precios deseado
    public List<Product> getByNamePrices(String name,String max, String min){
        List<Product> ProductList = repositoryProduct.getByNamePrices(name,max,min);
    
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    //Extrae lo productos de un precio menor al dado
    public List<Product> getByLessPrices(String max){
        List<Product> ProductList = repositoryProduct.getLessPrice(max);
     
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    //Extra productos de un precio mayor al dado
    public List<Product> getByMorePrice(String min){
        List<Product> ProductList = repositoryProduct.getMorePrice(min);
     
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    //Extrae productos de un precio entre un valor maximo y uno minimo
    public List<Product> getBetweenPrices(String max,String min){
        List<Product> ProductList = repositoryProduct.getPriceBetween(max,min);
     
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    //Devuleve lo productos que coincidan con un nombre dado y que tengan un precio mayor al dado
    public List<Product> getByNameMinPrice(String name,String min){
        List<Product> ProductList = repositoryProduct.getByNameMinPrice(name,min);
     
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
     //Devuleve lo productos que coincidan con un nombre dado y que tengan un precio menor al dado
    public List<Product> getByNameMaxPrice(String name,String max){
        List<Product> ProductList = repositoryProduct.getByNameMaxPrice(name,max);
     
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    
    public List<Product> getByOrder(Long id){
        List<Product> ProductList = repositoryProduct.getByOrder(id);
     
        if (ProductList.size() > 0) {
            return ProductList;
        } else {
            return new ArrayList<Product>();
        }
    }
    
}
