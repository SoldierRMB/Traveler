package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.PositionVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.service.StreetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CommonController", description = "通用接口")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final StreetService streetService;

    @Autowired
    public CommonController(StreetService streetService) {
        this.streetService = streetService;
    }

    @Operation(description = "通过街道编码获取位置信息")
    @GetMapping("/getPositionByStreetCode")
    public ResultVO<PositionVO> getPositionByStreetCode(@RequestParam Long streetCode) {
        return ResultVO.ok(streetService.getPositionByStreetCode(streetCode));
    }
}
