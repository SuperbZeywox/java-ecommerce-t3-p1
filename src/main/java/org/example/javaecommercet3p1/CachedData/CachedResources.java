package org.example.javaecommercet3p1.CachedData;

import jakarta.annotation.PostConstruct;
import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;
import org.example.javaecommercet3p1.Repos.Group1.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
//@Lazy
public class CachedResources {

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    List<ProductCategory> categories;
    // TODO: make these static values, thread safe
    //  each time i add, i have to update manually
    public static Map<Long, ProductCategory> categoriesIdIndex ;
    public static Map<String, ProductCategory> categoriesNameIndex;
    public static Map<Long, String > categoriesIdNameIndex ;
    // i need map of categoryId -> entityRepo
    // i need map of categoryName -> entityRepo
    public static final ResponseEntity NOT_FOUND = new ResponseEntity(HttpStatus.NOT_FOUND);
    public static final HttpStatus OK_STATUS = HttpStatus.OK;

//    public CachedResources() {
    @PostConstruct
    public void init() {
        // todo: check in case list is empty would stream() throw null pointer
        categories = productCategoryRepository.findAll();
        categoriesIdIndex = categories
                .stream()
                .collect(Collectors.toMap(ProductCategory::getId, productCategory -> productCategory));
        categoriesNameIndex = categories
                .stream()
                .collect(Collectors.toMap(ProductCategory::getCategoryName, productCategory -> productCategory));
        categoriesIdNameIndex = categories
                .stream()
                .collect(Collectors.toMap(ProductCategory::getId, productCategory -> productCategory.getCategoryName()));

    }



}
