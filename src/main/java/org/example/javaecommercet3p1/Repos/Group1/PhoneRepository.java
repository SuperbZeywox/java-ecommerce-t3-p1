package org.example.javaecommercet3p1.Repos.Group1;

import org.example.javaecommercet3p1.Entities.Group1.Phone;
import org.example.javaecommercet3p1.Repos.Template.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PhoneRepository")
public interface PhoneRepository extends ProductRepository<Phone, Long> {

//    List<Phone> findByNameContainingOrDescriptionContaining(String name, Pageable pageable);
//    List<Phone> findByNameOrDescriptionContaining(String name);


}
