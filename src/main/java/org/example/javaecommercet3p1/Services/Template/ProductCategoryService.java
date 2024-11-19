package org.example.javaecommercet3p1.Services.Template;

import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;
import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.Repos.Group1.DesktopRepository;
import org.example.javaecommercet3p1.Repos.Group1.PhoneRepository;
import org.example.javaecommercet3p1.Repos.Group1.ProductCategoryRepository;
import org.example.javaecommercet3p1.Repos.Template.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.javaecommercet3p1.CachedData.CachedResources.*;

@Service
public class ProductCategoryService {

    @Autowired
    DesktopRepository desktopRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // todo: use the cached data instead
    //  cached data must be update upon every adding of new entities
    public ResponseEntity<List<ProductCategory>> findCategories() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return categories.isEmpty() ? NOT_FOUND : new ResponseEntity<>(categories, OK_STATUS);
    }



    //    public ResponseEntity<List<Desktop>> findProductByCategoryId(Long id) {
//    public <T extends Product> List<T> findProductByCategoryId(Long id) {
    public <T extends Product> ResponseEntity<List<T>> findProductByCategoryId(Long id,int pageNumber, int pageSize) {
        // TODO: get which repo from id
        String categoryName = categoriesIdNameIndex.get(id);
        if (categoryName == null) {
            return NOT_FOUND;
        } else {
            ProductRepository repo = getRepo(categoryName);
            if (repo != null) {
                Pageable pageable = PageRequest.of(pageNumber,pageSize);
                List<T> categories = repo.findByCategoryId(id,pageable);
                return categories.isEmpty() ? NOT_FOUND : new ResponseEntity<>(categories, OK_STATUS);
            } else {
                return NOT_FOUND;// if forgot to add repo to cases
            }
        }
    }


    // adding repos is done manually, because i want it to be a local instance, not static
    // check if may be get bean through reflection would inject similar to autowire
    public ProductRepository getRepo(String categoryName) {
            switch (categoryName){
                case "Desktop":
                    return desktopRepository;
                case "Phone":
                    return phoneRepository;
                default:
                    return null;
            }
    }





}
