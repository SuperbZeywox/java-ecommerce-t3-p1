package org.example.javaecommercet3p1.Controllers.p2;

import org.example.javaecommercet3p1.Entities.Auth.p2.Country;
import org.example.javaecommercet3p1.Services.Auth.p2.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/shop/")
public class CountryController {

    @Autowired
    CountryService countryService;
    // findAllCountries "/api/countries"

//    @GetMapping(path = "/api/countries")
    @GetMapping(path = "countries")
    ResponseEntity<List<Country>> getAllCountries(){
        return countryService.findAllCountries();
    }
}
