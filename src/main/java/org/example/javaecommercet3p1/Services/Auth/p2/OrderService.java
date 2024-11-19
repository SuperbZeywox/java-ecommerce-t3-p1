package org.example.javaecommercet3p1.Services.Auth.p2;

import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.example.javaecommercet3p1.Repos.Auth.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.javaecommercet3p1.CachedData.CachedResources.NOT_FOUND;
import static org.example.javaecommercet3p1.CachedData.CachedResources.OK_STATUS;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<Page<Order>> findOrderByCustomerEmail(String email, int pageNumber, int pageSize) {
//    public Page<Order> findOrderByCustomerEmail(String email, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
//        Page<List<Order>> order = orderRepository.findOrdersByUserEmailOrderByDateCreatedDesc(email,pageable);
        Page<Order> ordersPage = orderRepository.findOrdersByUserEmail(email,pageable);
//        ordersPage.getContent().forEach(System.out::println);
//        System.out.println(ordersPage.getContent().size());
//        System.out.println(ordersPage.hasContent());
//        System.out.println(ordersPage!=null);
//        System.out.println(ordersPage != null && ordersPage.hasContent());


//        return order!= null ? new ResponseEntity<>(order, OK_STATUS) : NOT_FOUND;
//        return (order != null && order.hasContent()) ? new ResponseEntity<>(order, OK_STATUS) : NOT_FOUND;
//        return ordersPage;
        return (ordersPage != null && ordersPage.hasContent()) ? new ResponseEntity<>(ordersPage, OK_STATUS) : NOT_FOUND;
    }


}
