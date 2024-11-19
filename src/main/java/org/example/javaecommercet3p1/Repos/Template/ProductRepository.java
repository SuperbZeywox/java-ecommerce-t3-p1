package org.example.javaecommercet3p1.Repos.Template;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

//@Repository("ProductRepository")
@NoRepositoryBean
public interface ProductRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {

    T findByName(String name);

    List<T> findByCategoryId(Long id, Pageable pageable);
//    List<T> findByNameContaining(String name,Pageable pageable);
    List<T> findByNameContainingOrDescriptionContaining(String name, String keyWord2, Pageable pageable);

//    List<T> findTop3(Pageable pageable);
//    List<T> findTopByOOrderByIdAsc();
//    List<T> findAllByOrderByIdAsc();
//    List<T> findAllOrderByIdAsc();
//    List<T> findTop10ByOrderByIdAsc(Pageable pageable); // doesn't function well
//    List<T> findTop100ByOrderByIdAsc();
    List<T> findTop100ByOrderByIdAsc();






}
