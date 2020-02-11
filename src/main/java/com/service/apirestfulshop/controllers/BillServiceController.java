/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestfulshop.controllers;
import com.service.apirestfulshop.exceptions.RecordNotFoundException;
import com.service.apirestfulshop.model.Bill;
import com.service.apirestfulshop.services.BillService;
import java.util.Date;
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

import java.util.List;

/**
 *
 * @author Lidia
 */

    @RestController
    @RequestMapping("/bill")
    public class BillServiceController {

        @Autowired
        BillService service;

        @GetMapping
        public ResponseEntity<List<Bill>> getAllBill() {
            List<Bill> list = service.getAllBill();

            return new ResponseEntity<List<Bill>>(list, new HttpHeaders(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Bill> getBillById(@PathVariable("id") Long id)
                throws RecordNotFoundException {
            Bill entity = service.getBillById(id);

            return new ResponseEntity<Bill>(entity, new HttpHeaders(), HttpStatus.OK);
        }

        @GetMapping("/search/{datePurchase}")
        public ResponseEntity<List<Bill>> getBillByName(@PathVariable("datePurchase") Date datePurchase) {
            List<Bill> list = service.getBillByName(datePurchase);

            return new ResponseEntity<List<Bill>>(list, new HttpHeaders(), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Bill> createBill(@Valid @RequestBody Bill myBill) {
            Bill created = service.createBill(myBill);
            return new ResponseEntity<Bill>(created, new HttpHeaders(), HttpStatus.OK);
        }

        @PutMapping
        public ResponseEntity<Bill> UpdateBill(@Valid @RequestBody Bill myBill)
                throws RecordNotFoundException {
            Bill updated = service.createBill(myBill);
            return new ResponseEntity<Bill>(updated, new HttpHeaders(), HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public HttpStatus deleteBillById(@PathVariable("id") Long id)
                throws RecordNotFoundException {
            service.deleteBillById(id);
            return HttpStatus.ACCEPTED;
        }
    }
