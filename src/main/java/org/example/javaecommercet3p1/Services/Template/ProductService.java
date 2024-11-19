package org.example.javaecommercet3p1.Services.Template;

import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.Repos.Template.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.example.javaecommercet3p1.CachedData.CachedResources.NOT_FOUND;
import static org.example.javaecommercet3p1.CachedData.CachedResources.OK_STATUS;


//public class ProductUtils<T extends Product, V extends ProductRepository<T, Long>> {
public class ProductService<T extends Product, V extends ProductRepository<T, Long>> {

    // request sent to specific entity endpoints
    protected V repository;

    public ResponseEntity<T> findProductById(Long id) {
        Optional<T> product = repository.findById(id);
        return product.isPresent() ? new ResponseEntity<>(product.get(), OK_STATUS) : NOT_FOUND;
    }
    public ResponseEntity<List<T>> findByNameContaining(String name,int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
//        List<T> products = repository.findByNameOrDescriptionContaining(name,pageable);
        List<T> products = repository.findByNameContainingOrDescriptionContaining(name,name,pageable);
        System.out.println(products.size());
        products.forEach(System.out::println);
        return !products.isEmpty() ? new ResponseEntity<>(products, OK_STATUS) : NOT_FOUND;
    }

    public ResponseEntity<List<T>> findProducts() {
        List<T> products = repository.findTop100ByOrderByIdAsc();
        return !products.isEmpty() ? new ResponseEntity<>(products, OK_STATUS) : NOT_FOUND;
    }




}
