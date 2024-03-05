package com.soldiersoft.traveler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @GetMapping("/test")
    public String test() {
        return "staff";
    }
}
