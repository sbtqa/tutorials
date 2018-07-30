package ru.sbt.api.pojo;

public class Model {
    private String id;

    private String title;

    public Model(String id, String title) {
        this.id = id;
        this.title = title;
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

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", title = " + title + "]";
    }
}