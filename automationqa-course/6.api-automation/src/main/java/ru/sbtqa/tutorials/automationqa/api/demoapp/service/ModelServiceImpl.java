package ru.sbtqa.tutorials.automationqa.api.demoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbtqa.tutorials.automationqa.api.demoapp.dao.ManufacturerDao;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ManufacturerDao modelDao;

    @Override
    public Iterable getAll() {
        return modelDao.findAll();
    }

    @Override
    public Manufacturer getById(Integer id) {
        return modelDao.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (modelDao.existsById(id)) {
            modelDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public <S extends Manufacturer> S save(S entity) {
        return modelDao.save(entity);
    }
}
