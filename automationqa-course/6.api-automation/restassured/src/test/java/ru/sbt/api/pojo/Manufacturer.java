package ru.sbt.api.pojo;

public class Manufacturer {
    private String id;

    private Model[] models;

    private String title;

    private String country;

    public Manufacturer(String id, Model[] models, String title, String country) {
        this.id = id;
        this.models = models;
        this.title = title;
        this.country = country;
    }

    public Model[] getModels() {
        return models;
    }

    public void setModels(Model[] models) {
        this.models = models;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ClassPojo [models = " + models + ", id = " + id + ", title = " + title + ", country = " + country + "]";
    }
}