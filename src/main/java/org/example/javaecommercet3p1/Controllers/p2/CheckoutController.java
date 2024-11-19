package org.example.javaecommercet3p1.Controllers.p2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.javaecommercet3p1.Services.Auth.p2.CheckoutService;
import org.example.javaecommercet3p1.dto.Purchase;
import org.example.javaecommercet3p1.dto.PurchaseResponse;
import org.example.javaecommercet3p1.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/shop/")
//@RequestMapping("checkout/")
@RequestMapping("finance/")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

//    @PostMapping("api/checkout/purchase")
//    @PostMapping("checkout/purchase")
//    @PostMapping("purchase")
    @PostMapping("checkout")
    public ResponseEntity<PurchaseResponse> placeOrder(
            @RequestBody Purchase purchase,
            @RequestHeader("Authorization") String token
    ) {
//        System.out.println("checkout reached");
//        System.out.println(purchase);
        try {
            PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase,token);
            return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
