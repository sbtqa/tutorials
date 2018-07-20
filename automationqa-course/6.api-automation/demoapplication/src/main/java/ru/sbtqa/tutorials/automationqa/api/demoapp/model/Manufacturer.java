package ru.sbtqa.tutorials.automationqa.api.demoapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Manufacturer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    private String country;

    @EqualsAndHashCode.Exclude
    @Singular
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Model> models = new HashSet<>();

    public void addCar(Model model) {
        this.models.add(model);
    }
}
