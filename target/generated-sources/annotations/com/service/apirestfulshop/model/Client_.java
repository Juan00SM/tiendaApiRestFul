package com.service.apirestfulshop.model;

import com.service.apirestfulshop.model.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-05T12:36:38")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> phone;
    public static volatile SingularAttribute<Client, String> name;
    public static volatile SingularAttribute<Client, Integer> id;
    public static volatile SetAttribute<Client, Orders> ordersSet;
    public static volatile SingularAttribute<Client, Integer> age;

}