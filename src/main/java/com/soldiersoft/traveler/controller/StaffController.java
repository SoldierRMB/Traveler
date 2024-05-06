package com.soldiersoft.traveler.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.model.dto.AttractionAnnouncementDTO;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "StaffController", description = "景点管理员接口")
@RestController
@RequestMapping("/staff")
public class StaffController {
    private final AttractionService attractionService;
    private final UserAttractionService userAttractionService;
    private final TicketService ticketService;
    private final OrderService orderService;
    private final AttractionImageService attractionImageService;
    private final AttractionAnnouncementService attractionAnnouncementService;

    @Autowired
    public StaffController(AttractionService attractionService, UserAttractionService userAttractionService, TicketService ticketService, OrderService orderService, AttractionImageService attractionImageService, AttractionAnnouncementService attractionAnnouncementService) {
        this.attractionService = attractionService;
        this.userAttractionService = userAttractionService;
        this.ticketService = ticketService;
        this.orderService = orderService;
        this.attractionImageService = attractionImageService;
        this.attractionAnnouncementService = attractionAnnouncementService;
    }

    @Operation(description = "发布景点信息")
    @PostMapping("/publishAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<Attraction> publishAttraction(@RequestBody @Validated AttractionVO attractionVO, String username) {
        return ResultVO.ok(attractionService.publishAttraction(attractionVO, username));
    }

    @Operation(description = "更新景点信息")
    @PutMapping("/updateAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<Attraction> updateAttraction(@RequestBody @Validated AttractionVO attractionVO, @RequestParam String username) {
        return ResultVO.ok(attractionService.updateAttraction(attractionVO, username));
    }

    @Operation(description = "删除景点信息")
    @PutMapping("/deleteAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> deleteAttraction(@RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(attractionService.deleteAttraction(attractionId, username));
    }

    @Operation(description = "恢复删除景点信息")
    @PutMapping("/restoreAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> restoreAttraction(@RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(attractionService.restoreAttraction(attractionId, username));
    }

    @Operation(description = "通过用户名获取用户景点信息")
    @GetMapping("/getUserAttractionsByUsername")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<List<UserAttractionVO>> getUserAttractionsByUsername(@RequestParam String username) {
        return ResultVO.ok(userAttractionService.getUserAttractionsByUsername(username));
    }

    @Operation(description = "发布门票信息")
    @PostMapping("/publishTicket")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> publishTicket(@RequestBody AttractionTicketVO attractionTicketVO, @RequestParam String username) {
        return ResultVO.ok(ticketService.publishTicket(attractionTicketVO, username));
    }

    @Operation(description = "通过景点编号获取门票信息")
    @GetMapping("/getTicketsByAttractionId")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<List<TicketVO>> getTicketsByAttractionId(@RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(ticketService.getTicketsByAttractionId(attractionId, username));
    }

    @Operation(description = "更新门票信息")
    @PutMapping("/updateTicket")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> updateTicket(@RequestBody AttractionTicketVO attractionTicketVO, @RequestParam String username) {
        return ResultVO.ok(ticketService.updateTicket(attractionTicketVO, username));
    }

    @Operation(description = "删除门票信息")
    @PutMapping("/deleteTicket")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> deleteTicket(@RequestParam Long ticketId, @RequestParam String username) {
        return ResultVO.ok(ticketService.deleteTicket(ticketId, username));
    }

    @Operation(description = "通过景点编号获取景点订单信息")
    @GetMapping("/getOrdersByAttractionId")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<Page<OrderDTO>> getOrdersByAttractionId(@RequestParam Long attractionId, @RequestParam String username, @RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(orderService.getOrdersByAttractionId(attractionId, username, current, size));
    }

    @Operation(description = "通过订单编号使用门票")
    @PutMapping("/useTicket")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> useTicket(@RequestParam Long orderId, @RequestParam String username) {
        return ResultVO.ok(orderService.useTicket(orderId, username));
    }

    @Operation(description = "上传景点图片")
    @PostMapping("/uploadAttractionImage")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> uploadAttractionImage(@RequestParam("file") MultipartFile file, @RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(attractionImageService.uploadAttractionImage(file, attractionId, username));
    }

    @Operation(description = "更新景点图片")
    @PutMapping("/updateAttractionImage")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> updateAttractionImage(@RequestParam("file") MultipartFile file, @RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(attractionImageService.updateAttractionImage(file, attractionId, username));
    }

    @Operation(description = "发布景点公告信息")
    @PostMapping("/publishAttractionAnnouncement")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> publishAttractionAnnouncement(@RequestBody AttractionAnnouncementVO attractionAnnouncementVO, @RequestParam String username) {
        return ResultVO.ok(attractionAnnouncementService.publishAttractionAnnouncement(attractionAnnouncementVO, username));
    }

    @Operation(description = "更新景点公告信息")
    @PutMapping("/updateAttractionAnnouncement")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> updateAttractionAnnouncement(@RequestBody AttractionAnnouncementVO attractionAnnouncementVO, @RequestParam String username) {
        return ResultVO.ok(attractionAnnouncementService.updateAttractionAnnouncement(attractionAnnouncementVO, username));
    }

    @Operation(description = "删除景点公告信息")
    @DeleteMapping("/completeDeleteAttractionAnnouncement")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> completeDeleteAttractionAnnouncement(@RequestParam Long attractionAnnouncementId, @RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(attractionAnnouncementService.completeDeleteAttractionAnnouncement(attractionAnnouncementId, attractionId, username));
    }

    @Operation(description = "通过用户名获取所有景点公告信息")
    @GetMapping("/getAttractionAnnouncementsByUsername")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<Page<AttractionAnnouncementDTO>> getAttractionAnnouncementsByUsername(@RequestParam Long current, @RequestParam Long size, @RequestParam String username) {
        return ResultVO.ok(attractionAnnouncementService.getAttractionAnnouncementsByUsername(current, size, username));
    }
}
