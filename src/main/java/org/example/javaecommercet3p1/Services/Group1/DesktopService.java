package org.example.javaecommercet3p1.Services.Group1;

import jakarta.annotation.PostConstruct;
import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Repos.Group1.DesktopRepository;
import org.example.javaecommercet3p1.Services.Template.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesktopService extends ProductService<Desktop, DesktopRepository> {

    @Autowired
    DesktopRepository desktopRepository;



    @PostConstruct
    void setRepo() {
        super.repository = desktopRepository;
    }

    // implement extra entity specific methods if needed




}
