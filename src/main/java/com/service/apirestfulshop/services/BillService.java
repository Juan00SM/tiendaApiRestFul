/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestfulshop.services;


import com.service.apirestfulshop.exceptions.RecordNotFoundException;
import com.service.apirestfulshop.model.Bill;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.service.apirestfulshop.repositories.BillRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lidia
 */
@Service
public class BillService {

    @Autowired
    BillRepository repository;

    public List<Bill> getAllBill() {
        List<Bill> billList = repository.findAll();

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }

    public Bill getBillById(Long id) throws RecordNotFoundException {
        Optional<Bill> bill = repository.findById(id);

        if (bill.isPresent()) {
            return bill.get();
        } else {
            throw new RecordNotFoundException("No bill record exist for given id", id);
        }
    }

    public Bill createBill(Bill entity) {
        entity = repository.save(entity);
        return entity;
    }

    public Bill UpdateBill(Bill entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Bill> bill = repository.findById(entity.getId());

            if (bill.isPresent()) {
                Bill newEntity = bill.get();
                //newEntity.setId(entity.getId());
                newEntity.setDatePurchase(entity.getDatePurchase());
                newEntity.setNumProduct(entity.getNumProduct());
                newEntity.setTotalPrice(entity.getTotalPrice());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Bill not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of bill given", 0l);
        }
    }

    public void deleteBillById(Long id) throws RecordNotFoundException {
        Optional<Bill> bill = repository.findById(id);

        if (bill.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No bill record exist for given id", id);
        }
    }

    public List<Bill> getBillByName(Date datePurchase) {
        List<Bill> billList = repository.getByName(datePurchase);

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }
}
