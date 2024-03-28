package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;
import com.soldiersoft.traveler.service.AttractionService;
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
    private final AttractionService attractionService;

    @Autowired
    public StaffController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @Operation(description = "通过景点编号获取景点信息")
    @GetMapping("/staffGetAttractionById")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<AttractionVO> staffGetAttractionById(@RequestParam Long attractionId, String username) {
        return ResultVO.ok(attractionService.staffGetAttractionById(attractionId, username));
    }

    @Operation(description = "发布景点信息")
    @PostMapping("/postAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> postAttraction(@RequestBody @Validated UserAttractionVO userAttractionVO, String username) {
        return ResultVO.ok(attractionService.postAttraction(userAttractionVO, username));
    }

    @Operation(description = "更新景点信息")
    @PutMapping("/updateAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> updateAttraction(@RequestBody @Validated AttractionVO attractionVO, String username) {
        return ResultVO.ok(attractionService.updateAttraction(attractionVO, username));
    }

    @Operation(description = "删除景点信息")
    @PutMapping("/deleteAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> deleteAttraction(@RequestParam Long attractionId, String username) {
        return ResultVO.ok(attractionService.deleteAttraction(attractionId, username));
    }
}
