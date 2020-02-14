/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Client;
import com.service.apirestful.repositories.ClientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alfon
 */
@Service
public class ClientService {
    
    @Autowired
    ClientRepository repository;

    public List<Client> getAllClient() {
        List<Client> clientList = repository.findAll();

        if (clientList.size() > 0) {
            return clientList;
        } else {
            return new ArrayList<Client>();
        }
    }

    public Client getClientById(Long id) throws RecordNotFoundException {
        Optional<Client> client = repository.findById(id);

        if (client.isPresent()) {
            return client.get();
        } else {
            throw new RecordNotFoundException("No client record exist for given id", id);
        }
    }

    public Client createClient(Client entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Client updateClient(Client entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Client> client = repository.findById(entity.getId());

            if (client.isPresent()) {
                Client newEntity = client.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setAge(entity.getAge());
                newEntity.setPhone(entity.getPhone());
                newEntity.setOrders(entity.getOrders());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Client not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of client given", 0l);
        }
    }

    public void deleteClientById(Long id) throws RecordNotFoundException {
        Optional<Client> client = repository.findById(id);

        if (client.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No client record exist for given id", id);
        }
    }

    public List<Client> getClientByCriteria(String name, String age, String phone) {
        List<Client> clientList = repository.getByCriteria(name,age,phone);

        if (clientList.size() > 0) {
            return clientList;
        } else {
            return new ArrayList<Client>();
        }
    }
    
}
