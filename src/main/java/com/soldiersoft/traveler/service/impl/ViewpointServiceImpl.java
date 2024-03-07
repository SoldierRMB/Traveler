package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Viewpoint;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.ViewpointMapper;
import com.soldiersoft.traveler.model.vo.ViewpointVO;
import com.soldiersoft.traveler.service.ViewpointService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Soldier_RMB
 * @description 针对表【t_viewpoint(景点表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class ViewpointServiceImpl extends ServiceImpl<ViewpointMapper, Viewpoint>
        implements ViewpointService {

    @Override
    public ViewpointVO getViewpointById(Long id) {
        return null;
    }

    @Override
    public Boolean getViewpointIsPresent(String viewpointName) {
        Viewpoint viewpoint = lambdaQuery().eq(Viewpoint::getViewpointName, viewpointName).one();
        return Optional.ofNullable(viewpoint).isPresent();
    }

    @Override
    public String postViewpoint(ViewpointVO viewpointVO) {
        try {
            Viewpoint viewpoint = Viewpoint.builder()
                    .viewpointName(viewpointVO.getViewpointName())
                    .description(viewpointVO.getDescription())
                    .provinceCode(viewpointVO.getProvinceCode())
                    .cityCode(viewpointVO.getCityCode())
                    .areaCode(viewpointVO.getAreaCode())
                    .streetCode(viewpointVO.getStreetCode())
                    .build();
            save(viewpoint);
            return "景点发布成功，待管理员审核";
        } catch (Exception e) {
            throw new BizException("景点发布失败，请联系管理员", e);
        }
    }

    @Override
    public String updateViewpoint(ViewpointVO viewpointVO) {
        try {
            Viewpoint viewpoint = lambdaQuery().eq(Viewpoint::getViewpointName, viewpointVO.getViewpointName())
                    .one();
            Optional.ofNullable(viewpoint).orElseThrow(() -> new BizException("景点不存在"));
            lambdaUpdate()
                    .set(Viewpoint::getViewpointName, viewpointVO.getViewpointName())
                    .set(Viewpoint::getDescription, viewpointVO.getDescription())
                    .eq(Viewpoint::getId, viewpoint.getId());
            return "景点更新成功";
        } catch (Exception e) {
            throw new BizException("景点更新失败，请联系管理员", e);
        }
    }

    @Override
    public String deleteViewpoint(Long id) {
        try {
            lambdaUpdate()
                    .set(Viewpoint::getIsDeleted, 1)
                    .eq(Viewpoint::getId, id);
            return "景点删除成功";
        } catch (Exception e) {
            throw new BizException("景点删除失败，请联系管理员", e);
        }
    }
}




