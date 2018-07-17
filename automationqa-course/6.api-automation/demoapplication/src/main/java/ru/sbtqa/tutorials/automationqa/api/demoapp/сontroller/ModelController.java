package ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sbtqa.tutorials.automationqa.api.demoapp.service.ModelService;

@RestController
@RequestMapping("/car")
public class ModelController {

    @Autowired
    ModelService modelService;

    @RequestMapping(
            path = "models",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Iterable getManufacturers() {
        return modelService.getAll();
    }

}
