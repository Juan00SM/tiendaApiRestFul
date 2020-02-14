/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Bill;
import com.service.apirestful.repositories.BillRepository;
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

    public Bill updateBill(Bill entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Bill> bill = repository.findById(entity.getId());

            if (bill.isPresent()) {
                Bill newEntity = bill.get();
                //newEntity.setId(entity.getId());
                newEntity.setDatePurchase(entity.getDatePurchase());
                newEntity.setNumProduct(entity.getNumProduct());
                newEntity.setTotalPrice(entity.getTotalPrice());
                newEntity.setOrders(entity.getOrders());
                newEntity.setCode(entity.getCode());

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

    public List<Bill> getBillByCode(String code) {
        List<Bill> billList = repository.getByCode(code);

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }
       public List<Bill> getByLessTotalPrices(String max){
        List<Bill> billList = repository.getLessTotalPrice(max);
     
        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }
    
    public List<Bill> getByMoreTotalPrice(String min){
        List<Bill> billList = repository.getMoreTotalPrice(min);
     
        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }
    
    public List<Bill> getBetweenTotalPrices(String max,String min){
        List<Bill> billList = repository.getPriceTotalBetween(max,min);
     
        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }
    
}
