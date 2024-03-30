package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.AreaService;
import com.soldiersoft.traveler.service.CityService;
import com.soldiersoft.traveler.service.ProvinceService;
import com.soldiersoft.traveler.service.StreetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "CommonController", description = "通用接口")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final ProvinceService provinceService;
    private final CityService cityService;
    private final AreaService areaService;
    private final StreetService streetService;

    public CommonController(ProvinceService provinceService, CityService cityService, AreaService areaService, StreetService streetService) {
        this.provinceService = provinceService;
        this.cityService = cityService;
        this.areaService = areaService;
        this.streetService = streetService;
    }

    @Operation(description = "查询所有省份信息")
    @GetMapping("/getProvinces")
    public ResultVO<List<ProvinceVO>> getProvinces() {
        return ResultVO.ok(provinceService.getProvinces());
    }

    @Operation(description = "通过省份编码获取城市信息")
    @GetMapping("/getCitiesByProvinceCode")
    public ResultVO<List<CityVO>> getCitiesByProvinceCode(@RequestParam Long provinceCode) {
        return ResultVO.ok(cityService.getCitiesByProvinceCode(provinceCode));
    }

    @Operation(description = "通过城市编码获取县区信息")
    @GetMapping("/getAreasByCityCode")
    public ResultVO<List<AreaVO>> getAreasByCityCode(@RequestParam Long cityCode) {
        return ResultVO.ok(areaService.getAreasByCityCode(cityCode));
    }

    @Operation(description = "通过区域编码获取街道信息")
    @GetMapping("/getStreetsByAreaCode")
    public ResultVO<List<StreetVO>> getStreetsByAreaCode(@RequestParam Long areaCode) {
        return ResultVO.ok(streetService.getStreetsByAreaCode(areaCode));
    }
}
