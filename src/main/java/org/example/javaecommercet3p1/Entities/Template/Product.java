package org.example.javaecommercet3p1.Entities.Template;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.javaecommercet3p1.Entities.Group1.ProductCategory;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public long id;
    private String name;

    @Lob // longtext
    private String description;
    private String promoGift;
    private double discountRate;
    private double price;
    private double discountPrice;
    private int stockQuantity;
    private String deliveryOptions;
    private String availabilityRegions;

    private List<String> images;

    private String Warranty;
    private Date createdDate;
    private Date updatedDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", nullable = false) // stored in table as category_id, big int
    private ProductCategory category;

}
