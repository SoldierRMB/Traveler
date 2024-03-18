package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.ViewpointVO;
import com.soldiersoft.traveler.service.ViewpointService;
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
    private final ViewpointService viewpointService;

    @Autowired
    public TouristController(ViewpointService viewpointService) {
        this.viewpointService = viewpointService;
    }

    @Operation(description = "通过景点编号获取审核通过景点信息")
    @GetMapping("/getViewpointById")
    public ResultVO<ViewpointVO> getViewpointById(@RequestParam Long viewpointId) {
        return ResultVO.ok(viewpointService.getViewpointById(viewpointId));
    }

    @Operation(description = "通过城市编号获取所有审核通过景点信息")
    @GetMapping("/getViewpointsByCityCode")
    public ResultVO<List<ViewpointVO>> getViewpointsByCityCode(@RequestParam Long cityCode) {
        return ResultVO.ok(viewpointService.getViewpointsByCityCode(cityCode));
    }
}
