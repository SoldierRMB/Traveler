package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.ViewpointVO;
import com.soldiersoft.traveler.service.ViewpointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "StaffController", description = "景点管理员接口")
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final ViewpointService viewpointService;

    @Autowired
    public StaffController(ViewpointService viewpointService) {
        this.viewpointService = viewpointService;
    }

    @Operation(description = "通过景点编号获取景点信息")
    @GetMapping("/getViewpointById")
    public ResultVO<ViewpointVO> getViewpointById(@RequestParam Long id) {
        return ResultVO.ok(viewpointService.getViewpointById(id));
    }

    @Operation(description = "获取景点是否存在")
    @GetMapping("/getViewpointIsPresent")
    public ResultVO<Boolean> getViewpointIsPresent(@RequestParam String viewpointName) {
        return ResultVO.ok(viewpointService.getViewpointIsPresent(viewpointName));
    }

    @Operation(description = "发布景点信息")
    @PostMapping("/postViewpoint")
    public ResultVO<String> postViewpoint(@RequestBody ViewpointVO viewpointVO) {
        return ResultVO.ok(viewpointService.postViewpoint(viewpointVO));
    }

    @Operation(description = "更新景点信息")
    @PutMapping("/updateViewpoint")
    public ResultVO<String> updateViewpoint(@RequestBody ViewpointVO viewpointVO) {
        return ResultVO.ok(viewpointService.updateViewpoint(viewpointVO));
    }

    @Operation(description = "删除景点信息")
    @PutMapping("/deleteViewpoint")
    public ResultVO<String> deleteViewpoint(@RequestParam Long viewpointId) {
        return ResultVO.ok(viewpointService.deleteViewpoint(viewpointId));
    }
}
