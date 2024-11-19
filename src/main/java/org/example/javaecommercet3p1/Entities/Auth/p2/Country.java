package org.example.javaecommercet3p1.Entities.Auth.p2;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="country")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@ToString
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

//    @OneToMany(mappedBy = "country")
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<State> states = new ArrayList<State>();


    public void setStates(List<State> states) {
        states.forEach(state -> state.setCountry(this)); // necessary for deserialisation: json -> object
        this.states = states;
    }
}
