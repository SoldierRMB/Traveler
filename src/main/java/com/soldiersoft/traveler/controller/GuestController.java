package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.LocationVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.StreetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "GuestController", description = "游客接口")
@RestController
@RequestMapping("/guest")
public class GuestController {
    private final StreetService streetService;
    private final AttractionService attractionService;

    @Autowired
    public GuestController(StreetService streetService, AttractionService attractionService) {
        this.streetService = streetService;
        this.attractionService = attractionService;
    }

    @Operation(description = "通过街道编码获取位置信息")
    @GetMapping("/getPositionByStreetCode")
    public ResultVO<LocationVO> getPositionByStreetCode(@RequestParam Long streetCode) {
        return ResultVO.ok(streetService.getPositionByStreetCode(streetCode));
    }

    @Operation(description = "获取所有审核通过景点信息")
    @GetMapping("/getAttractions")
    public ResultVO<List<AttractionVO>> getAttractions() {
        return ResultVO.ok(attractionService.getAttractions());
    }
}
