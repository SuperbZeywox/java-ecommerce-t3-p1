package org.example.javaecommercet3p1.Services.Auth.p2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.example.javaecommercet3p1.Repos.Auth.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.example.javaecommercet3p1.CachedData.CachedResources.NOT_FOUND;
import static org.example.javaecommercet3p1.CachedData.CachedResources.OK_STATUS;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    public ResponseEntity<Page<Order>> findOrderByCustomerEmail(String token, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token.substring(7))
                .getBody();
        Page<Order> ordersPage = orderRepository.findOrdersByUserUsername(claims.getSubject(),pageable);
        return (ordersPage != null && ordersPage.hasContent()) ? new ResponseEntity<>(ordersPage, OK_STATUS) : NOT_FOUND;
    }


}
