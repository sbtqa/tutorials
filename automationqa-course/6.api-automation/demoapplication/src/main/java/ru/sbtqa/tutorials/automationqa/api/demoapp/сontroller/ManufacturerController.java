package ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.ManufacturerList;
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
    public Object getManufacturers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return manufacturerService.getById(id);
        }
        return new ManufacturerList(manufacturerService.getAll());
    }

    @RequestMapping(
            path = "manufacturers",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Позволяет добавить нового производителя в базу данных")
    public ResponseEntity setManufacturer(@RequestBody Manufacturer manufacturer) {
        Manufacturer createManufacturer = manufacturerService.save(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createManufacturer);
    }

    @RequestMapping(
            path = "manufacturers/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Позволяет удалять производителя из базы данных")
    public ResponseEntity deleteManufacturer(@PathVariable Integer id) {
        if (manufacturerService.deleteById(id)) {
            return ResponseEntity.ok().body("{success}");
        } else {
            throw new IllegalStateException("Нет производителя с таким ID");
        }
    }

    @RequestMapping(
            path = "manufacturers",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ApiOperation(value = "Позволяет обновлять производителя в базе данных")
    public ResponseEntity updateManufacturer(@RequestBody Manufacturer manufacturer) {
        if (manufacturerService.update(manufacturer)) {
            return ResponseEntity.ok().body("{success}");
        } else {
            throw new IllegalStateException("Нет производителя с таким ID");
        }
    }
}
