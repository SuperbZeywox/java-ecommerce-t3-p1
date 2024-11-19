package org.example.javaecommercet3p1.test1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javaecommercet3p1.Entities.Auth.p2.Country;
import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Entities.Group1.Phone;
import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;
import org.example.javaecommercet3p1.JavaEcommerceT3P1Application;
import org.example.javaecommercet3p1.Repos.Auth.CountryRepository;
import org.example.javaecommercet3p1.Repos.Group1.DesktopRepository;
//import org.junit.jupiter.api.Test;
import org.example.javaecommercet3p1.Repos.Group1.PhoneRepository;
import org.example.javaecommercet3p1.Repos.Group1.ProductCategoryRepository;
import org.example.javaecommercet3p1.test1.utils.CustomAnnotationIntrospector;
import org.example.javaecommercet3p1.test1.utils.JsonCustomParser;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)  //Add this
@SpringBootTest(classes = JavaEcommerceT3P1Application.class)
//@SpringBootTest
public class Test1 {
    @Autowired
    DesktopRepository desktopRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void runS3(){
        JsonCustomParser<Desktop,DesktopRepository> jp = new JsonCustomParser(Desktop.class,desktopRepository);
        jp.parseJson2("data.json","Desktop",productCategoryRepository);
    }
    @Test
    public void runS4(){
        JsonCustomParser<Phone, PhoneRepository> jp = new JsonCustomParser(Phone.class,phoneRepository);
        jp.parseJson2("data2.json","Phone",productCategoryRepository);
    }
    @Test
    public void runS6(){
        Optional<Desktop> desktop = desktopRepository.findById(1L);
        desktop.ifPresent(System.out::println);
    }

    @Test
    public void CachedResources2() {
        List<ProductCategory> all = productCategoryRepository.findAll();
        Map<Long, ProductCategory> collect = all.stream()
                .collect(Collectors.toMap(ProductCategory::getId, productCategory -> productCategory));
        collect.forEach((k,v) -> {
            System.out.println(k+":"+v);
        });
    }

    @Test
    public void persistCountriesAndStates() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setAnnotationIntrospector(new CustomAnnotationIntrospector());
            File file = new File("./src/test/java/org/example/javaecommercet3p1/test1/countries4.json");
            List<Country> countries = mapper.readValue(file, new TypeReference<List<Country>>() {});
            countryRepository.saveAll(countries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    



}
