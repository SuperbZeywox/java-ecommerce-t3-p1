package org.example.javaecommercet3p1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:multiple-db.properties")
//@PropertySource(value = "classpath:application.properties")
public class JavaEcommerceT3P1Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaEcommerceT3P1Application.class, args);
    }

}
