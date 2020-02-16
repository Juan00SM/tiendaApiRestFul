    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.apirestful.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bill")
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "datePurchase")
    @Temporal(TemporalType.DATE)
    private Date datePurchase;

    @Column(name = "numProduct")
    private int numProduct;

    @Column(name = "totalPrice")
    private int totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orders", referencedColumnName = "id")
    private Orders orders;

    public Bill() {
    }

    public Bill(Long id) {
        this.id = id;
    }

    public Bill(Long id, String code, Date datePurchase, int numProduct, int totalPrice) {
        this.id = id;
        this.code = code;
        this.datePurchase = datePurchase;
        this.numProduct = numProduct;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.code);
        hash = 59 * hash + Objects.hashCode(this.datePurchase);
        hash = 59 * hash + this.numProduct;
        hash = 59 * hash + this.totalPrice;
        hash = 59 * hash + Objects.hashCode(this.orders);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bill other = (Bill) obj;
        if (this.numProduct != other.numProduct) {
            return false;
        }
        if (this.totalPrice != other.totalPrice) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datePurchase, other.datePurchase)) {
            return false;
        }
        if (!Objects.equals(this.orders, other.orders)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", code=" + code + ", datePurchase=" + datePurchase + ", numProduct=" + numProduct + ", totalPrice=" + totalPrice + ", orders=" + orders + '}';
    }

    

}
