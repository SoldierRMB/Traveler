package com.soldiersoft.traveler.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TouristController", description = "游客用户接口")
@RestController
@RequestMapping("/tourist")
public class TouristController {
    private final AttractionService attractionService;
    private final TicketService ticketService;
    private final OrderService orderService;
    private final PostService postService;
    private final RatingService ratingService;
    private final CommentService commentService;

    @Autowired
    public TouristController(AttractionService attractionService, TicketService ticketService, OrderService orderService, PostService postService, RatingService ratingService, CommentService commentService) {
        this.attractionService = attractionService;
        this.ticketService = ticketService;
        this.orderService = orderService;
        this.postService = postService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }

    @Operation(description = "通过景点编号获取审核通过景点信息")
    @GetMapping("/getAttractionById")
    public ResultVO<AttractionVO> getAttractionById(@RequestParam Long attractionId) {
        return ResultVO.ok(attractionService.getAttractionById(attractionId));
    }

    @Operation(description = "通过城市编号获取所有审核通过景点信息")
    @GetMapping("/getAttractionsByCityCode")
    public ResultVO<List<AttractionVO>> getAttractionsByCityCode(@RequestParam Long cityCode) {
        return ResultVO.ok(attractionService.getAttractionsByCityCode(cityCode));
    }

    @Operation(description = "通过景点编号获取门票信息")
    @GetMapping("/getTicketsByAttractionId")
    public ResultVO<List<TicketVO>> getTicketsByAttractionId(@RequestParam Long attractionId) {
        return ResultVO.ok(ticketService.getTicketsByAttractionId(attractionId));
    }

    @Operation(description = "预订景点门票")
    @PostMapping("/booking")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<OrderVO> booking(@RequestBody OrderVO orderVO, String username) {
        return ResultVO.ok(orderService.booking(orderVO, username));
    }

    @Operation(description = "完成支付")
    @PutMapping("/completePayment")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> completePayment(@RequestParam Long orderId, String username) {
        return ResultVO.ok(orderService.completePayment(orderId, username));
    }

    @Operation(description = "获取用户订单列表")
    @GetMapping("/getUserOrders")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<Page<OrderDTO>> getUserOrders(@RequestParam String username, @RequestParam Long current, @RequestParam Long size) {
        return ResultVO.ok(orderService.getUserOrders(username, current, size));
    }

    @Operation(description = "取消订单")
    @PutMapping("/cancelOrder")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> cancelOrder(@RequestParam Long orderId, String username) {
        return ResultVO.ok(orderService.cancelOrder(orderId, username));
    }

    @Operation(description = "发布旅游动态信息")
    @PostMapping("/publishPost")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> publishPost(@RequestBody PostVO postVO, String username) {
        return ResultVO.ok(postService.publishPost(postVO, username));
    }

    @Operation(description = "评价已完成订单")
    @PostMapping("/rateCompleteOrder")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> rateCompleteOrder(@Valid @RequestBody RatingVO ratingVO, String username) {
        return ResultVO.ok(ratingService.rateCompleteOrder(ratingVO, username));
    }

    @Operation(description = "删除订单")
    @PutMapping("/deleteOrder")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> deleteOrder(@RequestParam Long orderId, String username) {
        return ResultVO.ok(orderService.deleteOrder(orderId, username));
    }

    @Operation(description = "评论动态")
    @PostMapping("/publishComment")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> publishComment(@RequestBody CommentVO commentVO, String username) {
        return ResultVO.ok(commentService.publishComment(commentVO, username));
    }
}
