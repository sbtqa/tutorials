package ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
@Api
public class SecuredController {
    @RequestMapping(
            path = "app",
            method = RequestMethod.GET
    )
    public ResponseEntity index() {
        return ResponseEntity.ok("{success}");
    }
}
