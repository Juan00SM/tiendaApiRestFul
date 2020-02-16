/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.services;

import com.service.apirestful.exceptions.RecordNotFoundException;
import com.service.apirestful.model.Bill;
import com.service.apirestful.model.Orders;
import com.service.apirestful.repositories.BillRepository;
import com.service.apirestful.repositories.OrdersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillService {

    @Autowired
    BillRepository repositoryBill;

    @Autowired
    OrdersRepository repositoryOrder;

    public List<Bill> getAllBill() {
        List<Bill> billList = repositoryBill.findAll();

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }

    public Bill getBillById(Long id) throws RecordNotFoundException {
        Optional<Bill> bill = repositoryBill.findById(id);

        if (bill.isPresent()) {
            return bill.get();
        } else {
            throw new RecordNotFoundException("No bill record exist for given id", id);
        }
    }

    public Bill createBill(Bill entity) {
        Orders o = entity.getOrders();
        if (o != null) {
            Orders or = repositoryOrder.findById(o.getId()).get();
            if (or != null) {
                entity.setOrders(or);
            }
        }
        entity = repositoryBill.save(entity);
        return entity;
    }

    public Bill updateBill(Bill entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<Bill> bill = repositoryBill.findById(entity.getId());

            if (bill.isPresent()) {
                Bill newEntity = bill.get();
                //newEntity.setId(entity.getId());
                newEntity.setDatePurchase(entity.getDatePurchase());
                newEntity.setNumProduct(entity.getNumProduct());
                newEntity.setTotalPrice(entity.getTotalPrice());
                newEntity.setCode(entity.getCode());

                Orders o = entity.getOrders();
                if (o != null) {
                    Orders or = repositoryOrder.findById(o.getId()).get();
                    if (or != null) {
                        o = or;
                    }
                    newEntity.setOrders(o);
                }
                

                newEntity = repositoryBill.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Bill not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of bill given", 0l);
        }
    }

    public void deleteBillById(Long id) throws RecordNotFoundException {
        Optional<Bill> bill = repositoryBill.findById(id);

        if (bill.isPresent()) {
            repositoryBill.deleteById(id);
        } else {
            throw new RecordNotFoundException("No bill record exist for given id", id);
        }
    }

    public List<Bill> getBillByCode(String code) {
        List<Bill> billList = repositoryBill.getByCode(code);

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }

    public List<Bill> getByLessTotalPrices(String max) {
        List<Bill> billList = repositoryBill.getLessTotalPrice(max);

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }

    public List<Bill> getByMoreTotalPrice(String min) {
        List<Bill> billList = repositoryBill.getMoreTotalPrice(min);

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }

    public List<Bill> getBetweenTotalPrices(String max, String min) {
        List<Bill> billList = repositoryBill.getPriceTotalBetween(max, min);

        if (billList.size() > 0) {
            return billList;
        } else {
            return new ArrayList<Bill>();
        }
    }

}
