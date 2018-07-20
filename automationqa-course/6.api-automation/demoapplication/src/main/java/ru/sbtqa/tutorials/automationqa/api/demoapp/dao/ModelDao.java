package ru.sbtqa.tutorials.automationqa.api.demoapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Model;

@RepositoryRestResource
public interface ModelDao extends CrudRepository<Model, Integer> {
}
