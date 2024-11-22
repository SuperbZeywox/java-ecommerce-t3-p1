package org.example.javaecommercet3p1.test1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.javaecommercet3p1.Entities.Group1.Desktop;
import org.example.javaecommercet3p1.Entities.Template.Product;
import org.example.javaecommercet3p1.JavaEcommerceT3P1Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)  //Add this
@SpringBootTest(classes = JavaEcommerceT3P1Application.class)
public class Test2 {

    @Autowired
    @Qualifier("group1EntityManager")
    private EntityManager entityManager;

    String keyword = "alza";
    @Test
    public void runT2() {

        TypedQuery<Desktop> query1 = entityManager.createQuery("SELECT e FROM Desktop e WHERE e.name like :condition", Desktop.class);
        query1.setParameter("condition", '%' + keyword + '%');
        List<Desktop> resultList = query1.getResultList();
        System.out.println(resultList.size());

    }


    // todo: this would require criteria builder
    public  <T extends Product> List<T> performSearchList(String keyWord, Class<T> clazz) {
        TypedQuery<T> query1 = entityManager.createQuery("SELECT e FROM Desktop e WHERE e.name like :condition", clazz);
        query1.setParameter("condition", '%' + keyword + '%');
        return query1.getResultList();
    }


}
