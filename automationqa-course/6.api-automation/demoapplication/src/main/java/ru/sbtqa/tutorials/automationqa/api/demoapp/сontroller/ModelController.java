package ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;
import ru.sbtqa.tutorials.automationqa.api.demoapp.service.ModelService;

@RestController
@RequestMapping("/app")
@Api
public class ModelController {

    @Autowired
    private ModelService carService;

    @RequestMapping(
            path = "car/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getCar(@PathVariable Integer id) {
        return ResponseEntity.ok().body(carService.getById(id));
    }

    @RequestMapping(
            path = "car",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity getCars() {
        return ResponseEntity.ok().body(carService.getAll());
    }

    @RequestMapping(
            path = "car",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity setCar(@RequestBody Manufacturer car) {
        carService.save(car);
        return ResponseEntity.ok().body("success");
    }
}
