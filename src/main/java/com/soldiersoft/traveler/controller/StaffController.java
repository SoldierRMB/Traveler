package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.ViewpointVO;
import com.soldiersoft.traveler.service.ViewpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final ViewpointService viewpointService;

    @Autowired
    public StaffController(ViewpointService viewpointService) {
        this.viewpointService = viewpointService;
    }

    @GetMapping("/getViewpointById")
    public ResultVO<ViewpointVO> getViewpointById(@RequestParam Long id) {
        return ResultVO.ok(viewpointService.getViewpointById(id));
    }

    @PostMapping("/postViewpoint")
    public ResultVO<String> postViewpoint(@RequestBody ViewpointVO viewpointVO) {
        if(viewpointService.getViewpointIsPresent(viewpointVO.getViewpointName()))
            return ResultVO.ok("该景点已存在");
        else
            return ResultVO.ok(viewpointService.postViewpoint(viewpointVO));
    }

    @PutMapping("/updateViewpoint")
    public ResultVO<String> updateViewpoint(@RequestBody ViewpointVO viewpointVO) {
        return ResultVO.ok(viewpointService.updateViewpoint(viewpointVO));
    }

    @PutMapping("/deleteViewpoint")
    public ResultVO<String> deleteViewpoint(@RequestParam Long id) {
        return ResultVO.ok(viewpointService.deleteViewpoint(id));
    }
}
