package org.example.javaecommercet3p1.Services.Auth.p2;

import org.example.javaecommercet3p1.Entities.Auth.p2.Country;
import org.example.javaecommercet3p1.Repos.Auth.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.javaecommercet3p1.CachedData.CachedResources.NOT_FOUND;
import static org.example.javaecommercet3p1.CachedData.CachedResources.OK_STATUS;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public ResponseEntity<List<Country>> findAllCountries() {
        List<Country> countries = countryRepository.findAll();
        System.out.println(countries.size());
        return countries.isEmpty() ? NOT_FOUND : new ResponseEntity<>(countries, OK_STATUS);
    }
}
