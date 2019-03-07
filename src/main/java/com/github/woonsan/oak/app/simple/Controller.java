package com.github.woonsan.oak.app.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/text/{id}")
    public String getTextContent(@PathVariable final String id) {
        return service.getTextContentById(id);
    }

    @PostMapping("/text")
    public String publishTextContent(@Valid @RequestBody final String text) {
        return service.publishTextContent(text);
    }

}
