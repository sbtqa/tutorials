package ru.sbtqa.tutorials.automationqa.api.demoapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;

@RepositoryRestResource
public interface ManufacturerDao extends CrudRepository<Manufacturer, Integer> {
}
