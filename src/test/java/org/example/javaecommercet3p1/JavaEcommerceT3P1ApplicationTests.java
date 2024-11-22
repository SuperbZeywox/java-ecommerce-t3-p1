package org.example.javaecommercet3p1;

import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.example.javaecommercet3p1.Repos.Auth.OrderRepository;
import org.example.javaecommercet3p1.Repos.Auth.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class JavaEcommerceT3P1ApplicationTests {

    //    @Test
//    void contextLoads() {
//
//    }
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void orderTest() {
        orderRepository.findAll().forEach(order -> System.out.println(order.getId()));
    }
//    @Test
//    void orderTest2() {
//        Pageable pageable = PageRequest.of(0,3);
//        orderRepository.findOrdersByUserEmailOrderByDateCreatedDesc("test@gmail.com", pageable).getContent()
//        .forEach(System.out::println);
//    }
    @Test
    void orderTest2() {
        Pageable pageable = PageRequest.of(0,3);
        orderRepository.findOrdersByUserEmailOrderByDateCreatedDesc("test@gmail.com")
                .forEach(System.out::println);
    }
//    @Test
//    void orderTest3() {
//        Pageable pageable = PageRequest.of(0,3);
//        orderRepository.findOrdersByUserEmail("test@gmail.com")
//                .forEach(System.out::println);
//    }
    @Test
    void orderTest3() {
        Pageable pageable = PageRequest.of(0,3);
        List<Order> content = orderRepository.findOrdersByUserUsername("test@gmail.com", pageable).getContent();
        content.forEach(System.out::println);
    }

}
