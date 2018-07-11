package ru.sbtqa.tutorials.automationqa.api.demoapp.сontroller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured/app")
@Api
public class SecuredController {
    @RequestMapping(
            path = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity index(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok("{success}");
    }
}
