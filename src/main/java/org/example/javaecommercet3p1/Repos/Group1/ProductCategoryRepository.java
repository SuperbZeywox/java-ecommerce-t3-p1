package org.example.javaecommercet3p1.Repos.Group1;

import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByCategoryName(String productCategoryName);

}
