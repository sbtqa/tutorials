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

import java.math.BigDecimal;

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
                    .model(new Model(null, "3", new BigDecimal(1_200_000)))
                    .model(new Model(null, "5", new BigDecimal(1_300_000)))
                    .model(new Model(null, "6", new BigDecimal(1_500_000)))
                    .model(new Model(null, "CX-5", new BigDecimal(1_450_000)))
                    .model(new Model(null, "CX-9", new BigDecimal(1_800_000)))
                    .build();

            Manufacturer kia = Manufacturer.builder()
                    .title("Kia Motors")
                    .country("South Korea")
                    .model(new Model(null, "cee'd", new BigDecimal(1_000_000)))
                    .model(new Model(null, "Cerato", new BigDecimal(1_100_000)))
                    .model(new Model(null, "Rio", new BigDecimal(800_000)))
                    .model(new Model(null, "Sorento", new BigDecimal(1_700_000)))
                    .model(new Model(null, "Soul", new BigDecimal(1_150_000)))
                    .model(new Model(null, "Sportage", new BigDecimal(1_500_000)))
                    .model(new Model(null, "Stinger", new BigDecimal(1_900_000)))
                    .model(new Model(null, "Optima", new BigDecimal(1_700_000)))
                    .build();

            Manufacturer toyota = Manufacturer.builder()
                    .title("Toyota Motor Corporation")
                    .country("Japan")
                    .model(new Model(null, "Allion", new BigDecimal(950_000)))
                    .model(new Model(null, "Avensis", new BigDecimal(1_100_000)))
                    .model(new Model(null, "Camry", new BigDecimal(1_200_000)))
                    .model(new Model(null, "Chaser", new BigDecimal(1_100_000)))
                    .model(new Model(null, "Corolla", new BigDecimal(1_000_000)))
                    .model(new Model(null, "FJ Cruiser", new BigDecimal(2_000_000)))
                    .model(new Model(null, "Highlander", new BigDecimal(2_200_000)))
                    .model(new Model(null, "Hulix", new BigDecimal(1_950_000)))
                    .model(new Model(null, "Land Cruiser", new BigDecimal(2_500_000)))
                    .model(new Model(null, "Land Cruiser Prado", new BigDecimal(2_300_000)))
                    .model(new Model(null, "Mark II", new BigDecimal(1_000_000)))
                    .model(new Model(null, "RAV4", new BigDecimal(1_400_000)))
                    .model(new Model(null, "Vitz", new BigDecimal(800_000)))
                    .build();


            manufacturerDao.save(mazda);
            manufacturerDao.save(kia);
            manufacturerDao.save(toyota);
        };
    }
}
