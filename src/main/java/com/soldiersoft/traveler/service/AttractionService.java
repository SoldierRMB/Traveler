package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.vo.AttractionVO;

import java.util.List;
import java.util.Map;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction(景点表)】的数据库操作Service
 * @createDate 2024-03-04 15:12:12
 */
public interface AttractionService extends IService<Attraction> {

    Boolean getAttractionIsPresent(Long attractionId);

    Map<Long, AttractionDTO> getAttractionsMapByUsername(String username);

    Attraction publishAttraction(AttractionVO attractionVO, String username);

    Attraction updateAttraction(AttractionVO attractionVO, String username);

    String deleteAttraction(Long attractionId, String username);

    String reviewAttractions(Long[] attractionIds, Boolean pass);

    AttractionVO getAttractionById(Long attractionId);

    List<AttractionVO> getAttractionsByCityCode(Long cityCode);

    Page<AttractionDTO> getAttractions(Long current, Long size);

    String restoreAttraction(Long attractionId, String username);

    String completeDeleteUserAttraction(Long attractionId);

    AttractionDTO getAttractionByOrderId(Long orderId);

    Page<AttractionDTO> getAttractionsByKeyword(String attractionName, Long cityCode, Long current, Long size);
}
