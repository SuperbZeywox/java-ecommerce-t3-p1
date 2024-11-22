package org.example.javaecommercet3p1.Repos.Auth;

import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    Page<List<Order>> findOrdersByUserEmailOrderByDateCreatedDesc(String email, Pageable pageable);
//    Page<List<Order>> findOrdersByUserEmailOrderByDateCreatedDesc(String email, Pageable pageable);
//    Page<List<Order>> findOrdersByUserEmailOrderByDateCreatedDesc(String email, Pageable pageable);

    List<Order> findOrdersByUserEmailOrderByDateCreatedDesc(String email);
//    List<Order> findOrdersByUserEmail(String email);

    Page<Order> findOrdersByUserUsername(String username, Pageable pageable);


}
