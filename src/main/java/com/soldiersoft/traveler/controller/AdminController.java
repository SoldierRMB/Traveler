package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.UserAttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AdminController", description = "系统管理员接口")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AttractionService attractionService;
    private final UserAttractionService userAttractionService;

    @Autowired
    public AdminController(AttractionService attractionService, UserAttractionService userAttractionService) {
        this.attractionService = attractionService;

        this.userAttractionService = userAttractionService;
    }

    @Operation(description = "批量审核景点")
    @PutMapping("/reviewAttractions")
    public ResultVO<String> reviewAttractions(@Validated @RequestBody Long[] attractionIds) {
        return ResultVO.ok(attractionService.reviewAttractions(attractionIds));
    }

    @Operation(description = "获取所有用户景点")
    @GetMapping("/getAllUserAttractions")
    public ResultVO<List<UserAttractionVO>> getAllUserAttractions() {
        return ResultVO.ok(userAttractionService.getAllUserAttractions());
    }

    @Operation(description = "获取所有未审核用户景点")
    @GetMapping("/getAllUnreviewedUserAttractions")
    public ResultVO<List<UserAttractionVO>> getAllUnreviewedUserAttractions() {
        return ResultVO.ok(userAttractionService.getAllUnreviewedUserAttractions());
    }
}
