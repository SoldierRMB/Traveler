package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.OrderVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.TicketVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.OrderService;
import com.soldiersoft.traveler.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Autowired
    public TouristController(AttractionService attractionService, TicketService ticketService, OrderService orderService) {
        this.attractionService = attractionService;
        this.ticketService = ticketService;
        this.orderService = orderService;
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
    public ResultVO<OrderVO> completePayment(@RequestParam Long orderId, String username) {
        return ResultVO.ok(orderService.completePayment(orderId, username));
    }

    @Operation(description = "获取用户订单列表")
    @GetMapping("/getUserOrders")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<List<OrderVO>> getUserOrders(@RequestParam String username) {
        return ResultVO.ok(orderService.getUserOrders(username));
    }
}
