package com.soldiersoft.traveler.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TouristController", description = "游客用户接口")
@RestController
@RequestMapping("/tourist")
public class TouristController {

}
