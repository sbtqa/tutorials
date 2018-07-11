package ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;
import ru.sbtqa.tutorials.automationqa.api.demoapp.service.ManufacturerService;

@RestController
@RequestMapping("/car")
@Api(value = "Car Manufacturers database", description = "Операции по работе с базой данных производителей автомобилей")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping(
            path = "manufacturers/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Возвращает информацию о производителе по ID", response = Manufacturer.class)
    public Manufacturer getManufacturer(@PathVariable Integer id) {
        return manufacturerService.getById(id);
    }

    @RequestMapping(
            path = "manufacturers",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Возвращает информацию о всех производителях", response = Iterable.class)
    public Iterable getManufacturers() {
        return manufacturerService.getAll();
    }

    @RequestMapping(
            path = "manufacturers",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Позволяет добавить нового производителя в базу данных")
    public ResponseEntity setManufacturer(@RequestBody Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        return ResponseEntity.ok().body("success");
    }

    @RequestMapping(
            path = "manufacturers",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Позволяет добавить нового производителя в базу данных")
    public ResponseEntity deleteManufacturer(@PathVariable Integer id) {
        if (manufacturerService.deleteById(id)) {
            return ResponseEntity.ok().body("success");
        } else {
            throw new IllegalStateException("Нет производителя с таким ID");
        }
    }

    @RequestMapping(
            path = "manufacturers",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Позволяет добавить нового производителя в базу данных")
    public ResponseEntity updateManufacturer(@RequestBody Manufacturer manufacturer) {
        if (manufacturerService.update(manufacturer)) {
            return ResponseEntity.ok().body("success");
        } else {
            throw new IllegalStateException("Нет производителя с таким ID");
        }
    }
}
