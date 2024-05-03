package com.soldiersoft.traveler.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soldiersoft.traveler.entity.Announcement;
import com.soldiersoft.traveler.model.dto.AttractionAnnouncementDTO;
import com.soldiersoft.traveler.model.dto.CommentDTO;
import com.soldiersoft.traveler.model.dto.PostDTO;
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
    private final AttractionService attractionService;
    private final FileService fileService;
    private final AttractionImageService attractionImageService;
    private final PostService postService;
    private final CommentService commentService;
    private final AnnouncementService announcementService;
    private final AttractionAnnouncementService attractionAnnouncementService;

    public CommonController(ProvinceService provinceService, CityService cityService, AreaService areaService, StreetService streetService, AttractionService attractionService, FileService fileService, AttractionImageService attractionImageService, PostService postService, CommentService commentService, AnnouncementService announcementService, AttractionAnnouncementService attractionAnnouncementService) {
        this.provinceService = provinceService;
        this.cityService = cityService;
        this.areaService = areaService;
        this.streetService = streetService;
        this.attractionService = attractionService;
        this.fileService = fileService;
        this.attractionImageService = attractionImageService;
        this.postService = postService;
        this.commentService = commentService;
        this.announcementService = announcementService;
        this.attractionAnnouncementService = attractionAnnouncementService;
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

    @Operation(description = "获取所有动态信息")
    @GetMapping("/getPosts")
    public ResultVO<Page<PostDTO>> getPosts(@RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(postService.getPosts(current, size));
    }

    @Operation(description = "通过动态编号获取评论信息")
    @GetMapping("/getCommentsByPostId")
    public ResultVO<Page<CommentDTO>> getCommentsByPostId(@RequestParam Long postId, @RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(commentService.getCommentsByPostId(postId, current, size));
    }

    @Operation(description = "获取所有系统公告信息")
    @GetMapping("/getAnnouncements")
    public ResultVO<Page<Announcement>> getAnnouncements(@RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(announcementService.getAnnouncements(current, size));
    }

    @Operation(description = "通过景点编号获取景点公告信息")
    @GetMapping("/getAttractionAnnouncementsByAttractionId")
    public ResultVO<Page<AttractionAnnouncementDTO>> getAttractionAnnouncementsByAttractionId(@RequestParam Long attractionId, @RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(attractionAnnouncementService.getAttractionAnnouncementsByAttractionId(attractionId, current, size));
    }
}
