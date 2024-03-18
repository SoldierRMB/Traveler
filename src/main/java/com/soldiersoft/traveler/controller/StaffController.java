package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserViewpointVO;
import com.soldiersoft.traveler.model.vo.ViewpointVO;
import com.soldiersoft.traveler.service.ViewpointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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

    @Operation(description = "景点管理员通过景点编号获取景点信息")
    @GetMapping("/staffGetViewpointById")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<ViewpointVO> staffGetViewpointById(@RequestParam Long viewpointId, String username) {
        return ResultVO.ok(viewpointService.staffGetViewpointById(viewpointId, username));
    }

    @Operation(description = "发布景点信息")
    @PostMapping("/postViewpoint")
    public ResultVO<String> postViewpoint(@RequestBody @Validated UserViewpointVO userViewpointVO) {
        return ResultVO.ok(viewpointService.postViewpoint(userViewpointVO));
    }

    @Operation(description = "更新景点信息")
    @PutMapping("/updateViewpoint")
    public ResultVO<String> updateViewpoint(@RequestBody @Validated ViewpointVO viewpointVO) {
        return ResultVO.ok(viewpointService.updateViewpoint(viewpointVO));
    }

    @Operation(description = "删除景点信息")
    @PutMapping("/deleteViewpoint")
    public ResultVO<String> deleteViewpoint(@RequestParam Long viewpointId) {
        return ResultVO.ok(viewpointService.deleteViewpoint(viewpointId));
    }
}
