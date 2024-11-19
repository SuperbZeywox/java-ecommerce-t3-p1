package org.example.javaecommercet3p1.Controllers.p2;

import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.example.javaecommercet3p1.Services.Auth.p2.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("finance/")
public class OrderController {
    @Autowired
    OrderService orderService;
//    // todo: change the name of this mapping on both front and back
    @GetMapping(path = "orders")
    ResponseEntity<Page<Order>> getOrderByCustomerEmail(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "pageNumber",defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ){
        System.out.println("order Reached");
        return orderService.findOrderByCustomerEmail(email,pageNumber,pageSize);
    }


}
