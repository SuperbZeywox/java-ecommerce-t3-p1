package org.example.javaecommercet3p1.Repos.Group1;

import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Repos.Template.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository("DesktopRepository")
public interface DesktopRepository extends ProductRepository<Desktop, Long> {

//    List<Desktop> findByCategoryId(Long id,Pageable pageable);
//    List<Desktop> findByCategoryId(Long id);

//    List<Desktop> findByNameContaining(String name,Pageable pageable);

    //    List<Desktop> findTop3(String name,Pageable pageable);
//    List<Desktop> findTopByOOrderByIdAsc();
//    List<Desktop> findAllByOOrderByIdAsc();
//    List<Desktop> findAll();
//    List<Desktop> findByNameContaining;


}
