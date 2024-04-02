package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.AttractionTicketService;
import com.soldiersoft.traveler.service.TicketService;
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
    private final AttractionTicketService attractionTicketService;
    private final TicketService ticketService;

    @Autowired
    public StaffController(AttractionService attractionService, UserAttractionService userAttractionService, AttractionTicketService attractionTicketService, TicketService ticketService) {
        this.attractionService = attractionService;
        this.userAttractionService = userAttractionService;
        this.attractionTicketService = attractionTicketService;
        this.ticketService = ticketService;
    }

    @Operation(description = "发布景点信息")
    @PostMapping("/publishAttraction")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> publishAttraction(@RequestBody @Validated AttractionVO attractionVO, String username) {
        return ResultVO.ok(attractionService.publishAttraction(attractionVO, username));
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

    @Operation(description = "发布景点门票信息")
    @PostMapping("/publishAttractionTicket")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<String> publishAttractionTicket(@RequestBody AttractionTicketVO attractionTicketVO, @RequestParam String username) {
        return ResultVO.ok(attractionTicketService.publishAttractionTicket(attractionTicketVO, username));
    }

    @Operation(description = "通过景点编号获取门票信息")
    @GetMapping("/getTicketsByAttractionId")
    @PreAuthorize("authentication.principal.equals(#username)")
    public ResultVO<List<TicketVO>> getTicketsByAttractionId(@RequestParam Long attractionId, @RequestParam String username) {
        return ResultVO.ok(ticketService.getTicketsByAttractionId(attractionId, username));
    }
}
