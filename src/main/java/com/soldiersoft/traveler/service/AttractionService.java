package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction(景点表)】的数据库操作Service
 * @createDate 2024-03-04 15:12:12
 */
public interface AttractionService extends IService<Attraction> {

    AttractionVO staffGetAttractionById(Long attractionId, String username);

    Boolean getAttractionIsPresent(Long attractionId);

    String postAttraction(UserAttractionVO userAttractionVO);

    String updateAttraction(AttractionVO attractionVO);

    String deleteAttraction(Long attractionId);

    String reviewAttractions(Long[] attractionIds);

    AttractionVO getAttractionById(Long attractionId);

    List<AttractionVO> getAttractionsByCityCode(Long cityCode);
}
