package net.itabc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/v1/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/v1/trigger")
    public void demo() {
        return;
    }

}
