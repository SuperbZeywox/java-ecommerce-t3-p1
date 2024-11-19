package org.example.javaecommercet3p1.Controllers.Group1;

import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.Services.Group1.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
//@RequestMapping("general/")
@RequestMapping("api/shop/group1/")
public class GeneralController {

    @Autowired
    GeneralService generalService;

    @GetMapping(path = "filter")
//    ResponseEntity<List<Object>> getDesktopByName(
//    List<Object> getProductByName(
    Map<String, List<? extends Product>> getProductByName(
            @RequestParam(name = "name") String name
    )  {
        try {
            System.out.println(name);
            Map<String, List<? extends Product>> objects = generalService.performParallelSearch(name);
            System.out.println("perforn Search Size: "+objects.size());
            return objects;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
