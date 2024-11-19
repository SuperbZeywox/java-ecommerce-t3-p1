package org.example.javaecommercet3p1.Repos.Auth;

import org.example.javaecommercet3p1.Entities.Auth.p2.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
