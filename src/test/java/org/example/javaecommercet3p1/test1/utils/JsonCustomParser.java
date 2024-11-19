package org.example.javaecommercet3p1.test1.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;
import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.Repos.Group1.ProductCategoryRepository;
import org.example.javaecommercet3p1.Repos.Template.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


//public class JsonCustomParser <T extends Product , V extends JpaRepository<T,Long>> {
public class JsonCustomParser <T extends Product , V extends ProductRepository> {
    Class<?> entityClass;
    V repository;


    public JsonCustomParser(Class<?> entityClass,V repository) {
        this.entityClass = entityClass;
        this.repository = repository;
    }

    public JsonCustomParser() {

    }
    public ProductCategory detachedCategory(String categoryName, ProductCategoryRepository repository) {
        ProductCategory category = repository.findByCategoryName(categoryName);
        if (category == null) {
            category = new ProductCategory(categoryName);
            repository.save(category);
        }
        return category;
    }

    public void parseJson2(String name,String categoryName,ProductCategoryRepository productCategoryRepository) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("./src/test/java/org/example/javaecommercet3p1/test1/"+name);
            JavaType contentType = TypeFactory.defaultInstance().constructCollectionType(List.class,entityClass);
            List<T> desktops = mapper.readValue(file, contentType);
            desktops.stream().forEach(product -> {
                product.setCreatedDate(new Date());
                product.setCategory(detachedCategory(categoryName,productCategoryRepository));
                repository.save(product);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }







}
