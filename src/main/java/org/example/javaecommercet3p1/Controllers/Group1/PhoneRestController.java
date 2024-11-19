package org.example.javaecommercet3p1.Controllers.Group1;

import jakarta.annotation.PostConstruct;
import org.example.javaecommercet3p1.Controllers.Templates.ProductRestController;
import org.example.javaecommercet3p1.Entities.Group1.Phone;
import org.example.javaecommercet3p1.Services.Group1.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("group1/phone/")
@RequestMapping("api/shop/group1/phone/")
public class PhoneRestController extends ProductRestController<Phone, PhoneService> {
    @Autowired
    PhoneService phoneService;

    @PostConstruct
    void setService() {
        super.service = phoneService;
    }










}
