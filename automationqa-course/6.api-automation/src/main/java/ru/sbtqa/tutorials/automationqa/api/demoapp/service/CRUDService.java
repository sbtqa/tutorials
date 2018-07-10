package ru.sbtqa.tutorials.automationqa.api.demoapp.service;

public interface CRUDService<T> {

    Iterable<T> getAll();

    <S extends T> S save(S entity);

    T getById(Integer id);

    boolean deleteById(Integer id);
}
