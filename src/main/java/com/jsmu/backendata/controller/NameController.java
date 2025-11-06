package com.jsmu.backendata.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsmu.backendata.service.NameService;

@RestController
@RequestMapping(value = "/api/names", produces = MediaType.APPLICATION_JSON_VALUE)
public class NameController {

    private final NameService nameService;

    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping
    public List<String> getNamesStartingWith(@RequestParam(value = "prefix", defaultValue = "San") String prefix) {
        return nameService.findNamesStartingWith(prefix);
    }
}
