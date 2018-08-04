package ru.sbt.api.pojo;

import java.math.BigDecimal;

public class Model {
    private String id;

    private String title;

    private BigDecimal averagePrice;

    public Model(String id, String title, BigDecimal averagePrice) {
        this.id = id;
        this.title = title;
        this.averagePrice = averagePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", averagePrice=" + averagePrice +
                '}';
    }
}