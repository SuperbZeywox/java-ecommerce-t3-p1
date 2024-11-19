package org.example.javaecommercet3p1.Services.Auth.p2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.javaecommercet3p1.Entities.Auth.User;
import org.example.javaecommercet3p1.Entities.Auth.p2.Order;
import org.example.javaecommercet3p1.Entities.Auth.p2.OrderItem;
import org.example.javaecommercet3p1.Repos.Auth.UserRepository;
import org.example.javaecommercet3p1.dto.Purchase;
import org.example.javaecommercet3p1.dto.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutService {

    @Autowired
    UserRepository customerRepository;

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

//    CustomerRepository customerRepository;
//    @Autowired
//    private DataSourceTransactionManager transactionManager;

    //    @Transactional
//    @Transactional(transactionManager="group1TransactionManager")
    @Transactional(transactionManager="authTransactionManager")
    public PurchaseResponse placeOrder(Purchase purchase,String token) {
        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        User customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

//        Customer customerFromDB = customerRepository.findByEmail(theEmail);
//        Customer customerFromDB = customerRepository.findCustomerByEmail(theEmail);
        /**
         * current functionality is, persist only f user exists, if not, then there is a problem in browser cache
         *
         */
//        token = token.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token.substring(7))
                .getBody();

        System.out.println(theEmail);
//        User customerFromDB = customerRepository.findUserByEmail(theEmail);
        Optional<User> customerFromDB = customerRepository.findByUsername(claims.getSubject());
        System.out.println("customerFromDB: " + customerFromDB);

//        if (customerFromDB != null) {
        if (customerFromDB.isPresent()) {
            // we found them ... let's assign them accordingly
            customer = customerFromDB.get();
        }
        customer.add(order);
        // save to the database
        /**
         * the exception causes the rollback if email doesn't exist = browser cache is rigged
         */
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }

}
