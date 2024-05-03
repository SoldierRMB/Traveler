package com.soldiersoft.traveler.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soldiersoft.traveler.model.dto.CommentDTO;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;
import com.soldiersoft.traveler.model.vo.AnnouncementVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;
import com.soldiersoft.traveler.service.*;
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
    private final UserRoleService userRoleService;
    private final PostService postService;
    private final CommentService commentService;
    private final AnnouncementService announcementService;

    @Autowired
    public AdminController(AttractionService attractionService, UserAttractionService userAttractionService, OrderService orderService, UserRoleService userRoleService, PostService postService, CommentService commentService, AnnouncementService announcementService) {
        this.attractionService = attractionService;
        this.userAttractionService = userAttractionService;
        this.orderService = orderService;
        this.userRoleService = userRoleService;
        this.postService = postService;
        this.commentService = commentService;
        this.announcementService = announcementService;
    }

    @Operation(description = "批量审核景点")
    @PutMapping("/reviewAttractions")
    public ResultVO<String> reviewAttractions(@Validated @RequestBody Long[] attractionIds, @RequestParam Boolean pass) {
        return ResultVO.ok(attractionService.reviewAttractions(attractionIds, pass));
    }

    @Operation(description = "获取所有用户景点")
    @GetMapping("/getUserAttractions")
    public ResultVO<List<UserAttractionVO>> getUserAttractions() {
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
        return ResultVO.ok(attractionService.completeDeleteUserAttraction(attractionId));
    }

    @Operation(description = "获取所有景点订单")
    @GetMapping("/getAllOrders")
    public ResultVO<Page<OrderDTO>> getAllOrders(@RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(orderService.getAllOrders(current, size));
    }

    @Operation(description = "获取所有用户角色信息")
    @GetMapping("/getUserRoles")
    public ResultVO<Page<UserRoleDTO>> getUserRoles(@RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(userRoleService.getUserRoles(current, size));
    }

    @Operation(description = "彻底删除动态信息")
    @DeleteMapping("/completeDeletePost")
    public ResultVO<String> completeDeletePost(@RequestParam Long postId) {
        return ResultVO.ok(postService.completeDeletePost(postId));
    }

    @Operation(description = "获取所有评论信息")
    @GetMapping("/getComments")
    public ResultVO<Page<CommentDTO>> getComments(@RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(commentService.getComments(current, size));
    }

    @Operation(description = "彻底删除评论信息")
    @DeleteMapping("/completeDeleteComment")
    public ResultVO<String> completeDeleteComment(@RequestParam Long commentId) {
        return ResultVO.ok(commentService.completeDeleteComment(commentId));
    }

    @Operation(description = "发布系统公告信息")
    @PostMapping("/publishAnnouncement")
    public ResultVO<String> publishAnnouncement(@RequestBody AnnouncementVO announcementVO) {
        return ResultVO.ok(announcementService.publishAnnouncement(announcementVO));
    }

    @Operation(description = "更新系统公告信息")
    @PutMapping("/updateAnnouncement")
    public ResultVO<String> updateAnnouncement(@RequestBody AnnouncementVO announcementVO) {
        return ResultVO.ok(announcementService.updateAnnouncement(announcementVO));
    }

    @Operation(description = "彻底删除系统公告信息")
    @DeleteMapping("/completeDeleteAnnouncement")
    public ResultVO<String> completeDeleteAnnouncement(@RequestParam Long announcementId) {
        return ResultVO.ok(announcementService.completeDeleteAnnouncement(announcementId));
    }
}
