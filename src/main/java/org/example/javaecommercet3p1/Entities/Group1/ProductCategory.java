package org.example.javaecommercet3p1.Entities.Group1;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.javaecommercet3p1.Entities.Template.Product;


import java.util.Set;

@Entity
@Table(name="product_category")
//@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "category_name", unique = true)
    private String categoryName;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

//    @OneToMany(targetEntity = Desktop.class, mappedBy = "category",fetch = FetchType.LAZY)
    @OneToMany(targetEntity = Desktop.class, mappedBy = "category")
    @JsonIgnore
    private Set<Desktop> desktops;

    @OneToMany(targetEntity = Phone.class, mappedBy = "category")
    @JsonIgnore
    private Set<Phone> phones;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
