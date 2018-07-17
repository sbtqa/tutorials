package ru.sbtqa.tutorials.automationqa.api.demoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbtqa.tutorials.automationqa.api.demoapp.dao.ManufacturerDao;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerDao manufacturerDao;

    @Override
    public Iterable getAll() {
        return manufacturerDao.findAll();
    }

    @Override
    public Manufacturer getById(Integer id) {
        return manufacturerDao.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (manufacturerDao.existsById(id)) {
            manufacturerDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public <S extends Manufacturer> S save(S entity) {
        return manufacturerDao.save(entity);
    }

    @Override
    public boolean update(Manufacturer entity) {
        Manufacturer manufacturer = manufacturerDao.findById(entity.getId()).orElse(null);
        if (manufacturer == null)
            return false;
        manufacturer.setModels(entity.getModels());
        manufacturer.setCountry(entity.getCountry());
        manufacturer.setTitle(entity.getTitle());
        manufacturerDao.save(manufacturer);
        return true;
    }
}
