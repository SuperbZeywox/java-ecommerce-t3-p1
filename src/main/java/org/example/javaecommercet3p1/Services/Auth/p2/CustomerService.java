package org.example.javaecommercet3p1.Services.Auth.p2;

import org.example.javaecommercet3p1.Entities.Auth.User;
import org.example.javaecommercet3p1.Repos.Auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.example.javaecommercet3p1.CachedData.CachedResources.NOT_FOUND;
import static org.example.javaecommercet3p1.CachedData.CachedResources.OK_STATUS;

@Service
public class CustomerService {

    @Autowired
//    CustomerRepository customerRepository;
    UserRepository userRepository;

    public ResponseEntity<User> findProductByCustomerEmail(String email) {
        User customer = userRepository.findUserByEmail(email);
        return customer!= null ? new ResponseEntity<>(customer, OK_STATUS) : NOT_FOUND;
    }

}
