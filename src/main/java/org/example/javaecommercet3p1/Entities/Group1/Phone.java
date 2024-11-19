package org.example.javaecommercet3p1.Entities.Group1;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.javaecommercet3p1.Entities.Template.Product;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Phone extends Product {
    // todo: later change to Integer
    private String storageCapacity;

    @ManyToOne(cascade = CascadeType.DETACH)
//    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false) // stored in table as category_id, big int
    private ProductCategory category;



}