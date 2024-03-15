package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Viewpoint;
import com.soldiersoft.traveler.model.vo.UserViewpointVO;
import com.soldiersoft.traveler.model.vo.ViewpointVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_viewpoint(景点表)】的数据库操作Service
 * @createDate 2024-03-04 15:12:12
 */
public interface ViewpointService extends IService<Viewpoint> {

    ViewpointVO getViewpointById(Long id);

    Boolean getViewpointIsPresent(Long viewpointId);

    String postViewpoint(UserViewpointVO userViewpointVO);

    String updateViewpoint(ViewpointVO viewpointVO);

    String deleteViewpoint(Long viewpointId);

    String reviewViewpoints(Long[] viewpointIds);
}
