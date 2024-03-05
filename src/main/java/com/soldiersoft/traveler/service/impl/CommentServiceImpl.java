package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Comment;
import com.soldiersoft.traveler.mapper.CommentMapper;
import com.soldiersoft.traveler.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_comment(评论表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}




