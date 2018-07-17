package ru.sbtqa.tutorials.automationqa.api.demoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbtqa.tutorials.automationqa.api.demoapp.dao.ModelDao;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Model;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    ModelDao modelDao;

    @Override
    public Iterable<Model> getAll() {
        return modelDao.findAll();
    }

    @Override
    public <S extends Model> S save(S entity) {
        return modelDao.save(entity);
    }

    @Override
    public boolean update(Model entity) {
        Model model = modelDao.findById(entity.getId()).orElse(null);
        if (model == null)
            return false;
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        modelDao.save(model);
        return true;
    }

    @Override
    public Model getById(Integer id) {
        return modelDao.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
