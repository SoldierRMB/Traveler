package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CommonController", description = "通用接口")
@RestController
@RequestMapping("/common")
public class CommonController {
    private final ProvinceService provinceService;
    private final CityService cityService;
    private final AreaService areaService;
    private final StreetService streetService;
    private final FileService fileService;
    private final AttractionImageService attractionImageService;

    public CommonController(ProvinceService provinceService, CityService cityService, AreaService areaService, StreetService streetService, FileService fileService, AttractionImageService attractionImageService) {
        this.provinceService = provinceService;
        this.cityService = cityService;
        this.areaService = areaService;
        this.streetService = streetService;
        this.fileService = fileService;
        this.attractionImageService = attractionImageService;
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

    @Operation(description = "通过图片名称获取图片")
    @GetMapping("/getImage/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        return fileService.getImage(imageName);
    }

    @Operation(description = "通过景点编号获取景点图片")
    @GetMapping("/getAttractionImageByAttractionId")
    public ResponseEntity<Resource> getAttractionImageByAttractionId(@RequestParam Long attractionId) {
        return attractionImageService.getAttractionImageByAttractionId(attractionId);
    }
}
