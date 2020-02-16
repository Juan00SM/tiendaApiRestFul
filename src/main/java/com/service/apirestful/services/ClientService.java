/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Client;
import com.service.apirestful.model.Orders;
import com.service.apirestful.repositories.ClientRepository;
import com.service.apirestful.repositories.OrdersRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    @Autowired
    ClientRepository repositoryClient;

    @Autowired
    OrdersRepository repositoryOrder;

    public List<Client> getAllClient() {
        List<Client> clientList = repositoryClient.findAll();

        if (clientList.size() > 0) {
            return clientList;
        } else {
            return new ArrayList<Client>();
        }
    }

    public Client getClientById(Long id) throws RecordNotFoundException {
        Optional<Client> client = repositoryClient.findById(id);

        if (client.isPresent()) {
            return client.get();
        } else {
            throw new RecordNotFoundException("No client record exist for given id", id);
        }
    }

    public Client createClient(Client entity) {
        Set<Orders> orders = new HashSet<>();
        Orders o;
        if (entity.getOrders() != null) {
            for (Orders order : entity.getOrders()) {
                o = repositoryOrder.findById(order.getId()).get();
                if (o == null) {
                    orders.add(order);
                } else {
                    orders.add(o);
                }
            }
        }

        entity.setOrders(orders);
        entity = repositoryClient.save(entity);
        return entity;
    }

    public Client updateClient(Client entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Client> client = repositoryClient.findById(entity.getId());

            if (client.isPresent()) {
                Client newEntity = client.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setAge(entity.getAge());
                newEntity.setPhone(entity.getPhone());

                Set<Orders> orders = new HashSet<>();
                if (entity.getOrders() != null) {
                    for (Orders order : entity.getOrders()) {
                        Orders o = repositoryOrder.findById(order.getId()).get();
                        if (o == null) {
                            orders.add(order);
                        } else {
                            orders.add(o);
                        }
                    }
                }
                newEntity.setOrders(orders);

                newEntity = repositoryClient.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Client not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of client given", 0l);
        }
    }

    public void deleteClientById(Long id) throws RecordNotFoundException {
        Optional<Client> client = repositoryClient.findById(id);

        if (client.isPresent()) {
            repositoryClient.deleteById(id);
        } else {
            throw new RecordNotFoundException("No client record exist for given id", id);
        }
    }

    public List<Client> getClientByCriteria(String name, String age, String phone) {
        List<Client> clientList = repositoryClient.getByCriteria(name, age, phone);

        if (clientList.size() > 0) {
            return clientList;
        } else {
            return new ArrayList<Client>();
        }
    }

}
