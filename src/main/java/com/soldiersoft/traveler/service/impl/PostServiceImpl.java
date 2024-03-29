package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Post;
import com.soldiersoft.traveler.mapper.PostMapper;
import com.soldiersoft.traveler.service.PostService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_post(动态表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {

}




