/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.controllers;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Client;
import com.service.apirestful.services.ClientService;
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
@RequestMapping("/client")
public class ClientServiceController {

    @Autowired
    ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClient() {
        List<Client> list = service.getAllClient();

        return new ResponseEntity<List<Client>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Client entity = service.getClientById(id);

        return new ResponseEntity<Client>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Client>> getClientByName(@PathVariable("name") String name) {
        List<Client> list = service.getClientByName(name);

        return new ResponseEntity<List<Client>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client myClient) {
        Client created = service.createClient(myClient);
        return new ResponseEntity<Client>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Client> UpdateClient(@Valid @RequestBody Client myClient)
            throws RecordNotFoundException {
        Client updated = service.updateClient(myClient);
        return new ResponseEntity<Client>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteClientById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteClientById(id);
        return HttpStatus.ACCEPTED;
    }
}
