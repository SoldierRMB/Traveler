package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.UserAttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "StaffController", description = "景点管理员接口")
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final AttractionService attractionService;
    private final UserAttractionService userAttractionService;

    @Autowired
    public StaffController(AttractionService attractionService, UserAttractionService userAttractionService) {
        this.attractionService = attractionService;
        this.userAttractionService = userAttractionService;
    }

    @Operation(description = "发布景点信息")
    @PostMapping("/postAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> postAttraction(@RequestBody @Validated AttractionVO attractionVO, String username) {
        return ResultVO.ok(attractionService.postAttraction(attractionVO, username));
    }

    @Operation(description = "更新景点信息")
    @PutMapping("/updateAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> updateAttraction(@RequestBody @Validated AttractionVO attractionVO, @RequestParam String username) {
        return ResultVO.ok(attractionService.updateAttraction(attractionVO, username));
    }

    @Operation(description = "删除景点信息")
    @PutMapping("/deleteAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> deleteAttraction(@RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(attractionService.deleteAttraction(attractionId, username));
    }

    @Operation(description = "通过用户名获取用户景点信息")
    @GetMapping("/getUserAttractionsByUsername")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<List<UserAttractionVO>> getUserAttractionsByUsername(@RequestParam String username) {
        return ResultVO.ok(userAttractionService.getUserAttractionsByUsername(username));
    }
}
