package ru.sbtqa.tutorials.automationqa.api.demoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Model {

    @Id
    @GeneratedValue
    @XmlAttribute
    private Integer id;

    @Column(nullable = false)
    @XmlAttribute
    private String title;

    @Column(nullable = false)
    @XmlAttribute
    private BigDecimal averagePrice;
}


