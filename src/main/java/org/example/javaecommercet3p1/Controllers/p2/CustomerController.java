package org.example.javaecommercet3p1.Controllers.p2;

import org.example.javaecommercet3p1.Entities.Auth.User;
import org.example.javaecommercet3p1.Services.Auth.p2.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/shop/")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    // todo: change the name of this mapping on both front and back
    @GetMapping(path = "findByEmail")
    ResponseEntity<User> getProductByCustomerEmail(
            @RequestParam(name = "email") String email
    ){
        return customerService.findProductByCustomerEmail(email);
    }

}
