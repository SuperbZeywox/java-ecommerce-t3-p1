package org.example.javaecommercet3p1.Controllers.Templates;

import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.Services.Template.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ProductRestController<T extends Product,V extends ProductService> {

    // I am not supposed to pass repo as param from here
    protected V service;
//
    @GetMapping(path = "{id}")
    ResponseEntity<T> getDesktop(@PathVariable Long id){
        return service.findProductById(id);
    }

    @GetMapping(path = "filter")
    ResponseEntity<List<T>> getDesktopByName(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "pageNumber",defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ){
        return service.findByNameContaining(name,pageNumber,pageSize);
    }

    @GetMapping(path = "products")
    ResponseEntity<List<T>> getDesktopProducts(){
        return service.findProducts();
    }


}
