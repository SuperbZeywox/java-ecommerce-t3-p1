package org.example.javaecommercet3p1.Controllers.Group1;

import jakarta.annotation.PostConstruct;
import org.example.javaecommercet3p1.Controllers.Templates.ProductRestController;
import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Services.Group1.DesktopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("group1/desktop/")
@RequestMapping("api/shop/group1/desktop/")
public class DesktopRestController extends ProductRestController<Desktop, DesktopService> {
    @Autowired
    DesktopService desktopService;

    @PostConstruct
    void setService() {
        super.service = desktopService;
    }








}
