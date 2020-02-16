/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Bill;
import com.service.apirestful.model.Client;
import com.service.apirestful.model.Orders;
import com.service.apirestful.model.Product;
import com.service.apirestful.repositories.BillRepository;
import com.service.apirestful.repositories.ClientRepository;
import com.service.apirestful.repositories.OrdersRepository;
import com.service.apirestful.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {

    @Autowired
    OrdersRepository repositoryOrder;

    @Autowired
    ClientRepository repositoryClient;

    @Autowired
    BillRepository repositoryBill;

    @Autowired
    ProductRepository repositoryProduct;

    public List<Orders> getAllOrders() {
        List<Orders> ordersList = repositoryOrder.findAll();

        if (ordersList.size() > 0) {
            return ordersList;
        } else {
            return new ArrayList<Orders>();
        }
    }

    public Orders getOrdersById(Long id) throws RecordNotFoundException {
        Optional<Orders> orders = repositoryOrder.findById(id);

        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new RecordNotFoundException("No orders record exist for given id", id);
        }
    }

    public Orders createOrders(Orders entity) {

        Bill bill = entity.getBill();
        if (bill != null) {
            Bill b = repositoryBill.findById(bill.getId()).get();
            if (b != null) {
                entity.setBill(b);
            }
        }

        Client client = entity.getClient();
        if (client != null) {
            Client c = repositoryClient.findById(client.getId()).get();
            if (c != null) {
                entity.setClient(c);
            }
        }

        Set<Product> products = new HashSet<>();
        if (entity.getProducts() != null) {
            for (Product product : entity.getProducts()) {
                Product p = repositoryProduct.findById(product.getId()).get();
                if (p == null) {
                    products.add(product);
                } else {
                    products.add(p);
                }
            }
        }

        entity.setProducts(products);
        entity = repositoryOrder.save(entity);
        return entity;
    }

    public Orders updateOrders(Orders entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Orders> orders = repositoryOrder.findById(entity.getId());

            if (orders.isPresent()) {
                Orders newEntity = orders.get();
                //newEntity.setId(entity.getId());
                Client client = entity.getClient();
                if (client != null) {
                    Client c = repositoryClient.findById(client.getId()).get();
                    if (c != null) {
                        client = c;
                    }
                    newEntity.setClient(client);
                }

                newEntity.setStatus(entity.getStatus());
                newEntity.setCreationDate(entity.getCreationDate());

                Bill bill = entity.getBill();
                if (bill != null) {
                    Bill b = repositoryBill.findById(bill.getId()).get();
                    if (b != null) {
                        bill = b;
                    }
                    newEntity.setBill(bill);
                }

                Set<Product> products = new HashSet<>();
                if (entity.getProducts() != null) {
                    for (Product product : entity.getProducts()) {
                        Product p = repositoryProduct.findById(product.getId()).get();
                        if (p == null) {
                            products.add(product);
                        } else {
                            products.add(p);
                        }
                    }
                }
                newEntity.setProducts(products);

                newEntity = repositoryOrder.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Orders not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of orders given", 0l);
        }
    }

    public void deleteOrdersById(Long id) throws RecordNotFoundException {
        Optional<Orders> orders = repositoryOrder.findById(id);

        if (orders.isPresent()) {
            repositoryOrder.deleteById(id);
        } else {
            throw new RecordNotFoundException("No orders record exist for given id", id);
        }
    }

    public List<Orders> getClientByCriteria(String date, String status) {
        List<Orders> ordersList = repositoryOrder.getByCriteria(date,status);

        if (ordersList.size() > 0) {
            return ordersList;
        } else {
            return new ArrayList<Orders>();
        }
    }

}
