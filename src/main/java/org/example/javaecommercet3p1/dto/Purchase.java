package org.example.javaecommercet3p1.dto;

import lombok.Data;
import lombok.ToString;
import org.example.javaecommercet3p1.Entities.Auth.User;
import org.example.javaecommercet3p1.Entities.Auth.p2.Address;
import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.example.javaecommercet3p1.Entities.Auth.p2.OrderItem;

import java.util.Set;

@Data
@ToString
public class Purchase {

    private User customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
