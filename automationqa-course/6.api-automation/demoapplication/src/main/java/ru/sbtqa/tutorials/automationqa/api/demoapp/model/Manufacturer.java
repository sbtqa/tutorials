package ru.sbtqa.tutorials.automationqa.api.demoapp.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@XmlRootElement
public class Manufacturer {

    @Id
    @GeneratedValue
    @XmlAttribute
    private Integer id;

    @Column(unique = true, nullable = false)
    @XmlAttribute
    private String title;

    @XmlAttribute
    private String country;

    @EqualsAndHashCode.Exclude
    @Singular
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElement
    private Set<Model> models = new HashSet<>();

    public void addCar(Model model) {
        this.models.add(model);
    }
}
