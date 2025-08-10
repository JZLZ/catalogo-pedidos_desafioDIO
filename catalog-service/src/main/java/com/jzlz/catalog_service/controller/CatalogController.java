package com.jzlz.catalog_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

    @GetMapping("/list")
    public String list() {
        return "Catalog online! 🟢";
    }
}
