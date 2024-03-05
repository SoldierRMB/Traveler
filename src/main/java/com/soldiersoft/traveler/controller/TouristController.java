package com.soldiersoft.traveler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tourist")
public class TouristController {
    @GetMapping("/test")
    public String test() {
        return "tourist";
    }
}
