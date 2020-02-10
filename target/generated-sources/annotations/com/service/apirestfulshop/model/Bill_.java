package com.service.apirestfulshop.model;

import com.service.apirestfulshop.model.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-05T12:36:38")
@StaticMetamodel(Bill.class)
public class Bill_ { 

    public static volatile SingularAttribute<Bill, Integer> numProduct;
    public static volatile SingularAttribute<Bill, Orders> idOrder;
    public static volatile SingularAttribute<Bill, Integer> totalPrice;
    public static volatile SingularAttribute<Bill, Date> datePurchase;
    public static volatile SingularAttribute<Bill, Integer> id;

}