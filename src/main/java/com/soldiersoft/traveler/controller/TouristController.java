package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "TouristController", description = "游客用户接口")
@RestController
@RequestMapping("/tourist")
public class TouristController {
    private final AttractionService attractionService;

    @Autowired
    public TouristController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @Operation(description = "通过景点编号获取审核通过景点信息")
    @GetMapping("/getAttractionById")
    public ResultVO<AttractionVO> getAttractionById(@RequestParam Long attractionId) {
        return ResultVO.ok(attractionService.getAttractionById(attractionId));
    }

    @Operation(description = "通过城市编号获取所有审核通过景点信息")
    @GetMapping("/getAttractionsByCityCode")
    public ResultVO<List<AttractionVO>> getAttractionsByCityCode(@RequestParam Long cityCode) {
        return ResultVO.ok(attractionService.getAttractionsByCityCode(cityCode));
    }
}
