package ru.sbtqa.tutorials.automationqa.api.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import ru.sbtqa.tutorials.automationqa.api.demoapp.dao.ManufacturerDao;
import ru.sbtqa.tutorials.automationqa.api.demoapp.dao.ModelDao;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Manufacturer;
import ru.sbtqa.tutorials.automationqa.api.demoapp.model.Model;

@SpringBootApplication
public class DemoAppApplication {

    @Autowired
    ModelDao modelDao;

    @Autowired
    ManufacturerDao manufacturerDao;

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> init() {
        return (contextStartedEvent) -> {

            Manufacturer mazda = Manufacturer.builder()
                    .title("Mazda")
                    .country("Japan")
                    .model(new Model(null, "3"))
                    .model(new Model(null, "5"))
                    .model(new Model(null, "6"))
                    .model(new Model(null, "CX-5"))
                    .model(new Model(null, "CX-9"))
                    .build();

            Manufacturer kia = Manufacturer.builder()
                    .title("Kia Motors")
                    .country("South Korea")
                    .model(new Model(null, "cee'd"))
                    .model(new Model(null, "Cerato"))
                    .model(new Model(null, "Rio"))
                    .model(new Model(null, "Sorento"))
                    .model(new Model(null, "Soul"))
                    .model(new Model(null, "Sportage"))
                    .model(new Model(null, "Stinger"))
                    .model(new Model(null, "Optima"))
                    .build();

            Manufacturer toyota = Manufacturer.builder()
                    .title("Toyota Motor Corporation")
                    .country("Japan")
                    .model(new Model(null, "Allion"))
                    .model(new Model(null, "Avensis"))
                    .model(new Model(null, "Camry"))
                    .model(new Model(null, "Chaser"))
                    .model(new Model(null, "Corolla"))
                    .model(new Model(null, "FJ Cruiser"))
                    .model(new Model(null, "Highlander"))
                    .model(new Model(null, "Hulix"))
                    .model(new Model(null, "Land Cruiser"))
                    .model(new Model(null, "Land Cruiser Prado"))
                    .model(new Model(null, "Mark II"))
                    .model(new Model(null, "RAV4"))
                    .model(new Model(null, "Vitz"))
                    .build();


            manufacturerDao.save(mazda);
            manufacturerDao.save(kia);
            manufacturerDao.save(toyota);
        };
    }
}
