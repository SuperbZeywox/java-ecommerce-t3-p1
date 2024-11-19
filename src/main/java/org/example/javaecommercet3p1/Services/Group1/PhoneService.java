package org.example.javaecommercet3p1.Services.Group1;

import jakarta.annotation.PostConstruct;
import org.example.javaecommercet3p1.Entities.Group1.Phone;
import org.example.javaecommercet3p1.Repos.Group1.PhoneRepository;
import org.example.javaecommercet3p1.Services.Template.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService extends ProductService<Phone, PhoneRepository> {

    @Autowired
    PhoneRepository phoneRepository;


    @PostConstruct
    void setRepo() {
        super.repository = phoneRepository;
    }




}
