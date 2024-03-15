package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.service.ViewpointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AdminController", description = "系统管理员接口")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ViewpointService viewpointService;

    @Autowired
    public AdminController(ViewpointService viewpointService) {
        this.viewpointService = viewpointService;

    }

    @Operation(description = "批量审核景点")
    @PutMapping("/reviewViewpoints")
    public ResultVO<String> reviewViewpoints(@Validated @RequestBody Long[] viewpointIds) {
        return ResultVO.ok(viewpointService.reviewViewpoints(viewpointIds));
    }
}
