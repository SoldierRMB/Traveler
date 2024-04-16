package com.soldiersoft.traveler.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.OrderService;
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
    private final OrderService orderService;

    @Autowired
    public AdminController(AttractionService attractionService, UserAttractionService userAttractionService, OrderService orderService) {
        this.attractionService = attractionService;
        this.userAttractionService = userAttractionService;
        this.orderService = orderService;
    }

    @Operation(description = "批量审核景点")
    @PutMapping("/reviewAttractions")
    public ResultVO<String> reviewAttractions(@Validated @RequestBody Long[] attractionIds, @RequestParam Boolean pass) {
        return ResultVO.ok(attractionService.reviewAttractions(attractionIds, pass));
    }

    @Operation(description = "获取所有用户景点")
    @GetMapping("/getUserAttractions")
    public ResultVO<List<UserAttractionVO>> getAllUserAttractions() {
        return ResultVO.ok(userAttractionService.getUserAttractions(true));
    }

    @Operation(description = "获取所有未审核用户景点")
    @GetMapping("/getUnreviewedUserAttractions")
    public ResultVO<List<UserAttractionVO>> getUnreviewedUserAttractions() {
        return ResultVO.ok(userAttractionService.getUserAttractions(false));
    }

    @Operation(description = "彻底删除用户景点")
    @DeleteMapping("/completeDeleteUserAttraction")
    public ResultVO<String> completeDeleteUserAttraction(@RequestParam Long attractionId) {
        return ResultVO.ok(userAttractionService.completeDeleteUserAttraction(attractionId));
    }

    @Operation(description = "获取所有景点订单")
    @GetMapping("/getAllOrders")
    public ResultVO<Page<OrderDTO>> getAllOrders(@RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(orderService.getAllOrders(current, size));
    }
}
