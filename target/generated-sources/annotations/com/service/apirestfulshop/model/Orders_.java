package com.service.apirestfulshop.model;

import com.service.apirestfulshop.model.Bill;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-05T12:36:38")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Date> dateCreated;
    public static volatile SingularAttribute<Orders, Long> idClient;
    public static volatile SetAttribute<Orders, Bill> billSet;
    public static volatile SingularAttribute<Orders, Long> id;
    public static volatile SingularAttribute<Orders, String> status;

}