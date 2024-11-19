package org.example.javaecommercet3p1.Controllers.Group1;

import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;
import org.example.javaecommercet3p1.Services.Group1.DesktopService;
import org.example.javaecommercet3p1.Services.Template.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("categories")
@RequestMapping("api/shop/categories")
public class ProductCategoryRestController {
    @Autowired
    DesktopService desktopService;
    @Autowired
    ProductCategoryService productCategoryService;

    // todo: use the cached data instead
    //  cached data must be update upon every adding of new entities
    @GetMapping(path = "/")
    ResponseEntity<List<ProductCategory>> getCategories(){
        return productCategoryService.findCategories();
    }


    @GetMapping(path = "findByCategoryId")
    ResponseEntity<List<Desktop>> getProductByCategoryId(
//    List<Desktop> getProductByCategoryId(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "pageNumber",defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
        ){
        return productCategoryService.findProductByCategoryId(id,pageNumber,pageSize);
    }




}
