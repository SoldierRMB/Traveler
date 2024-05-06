package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Comment;
import com.soldiersoft.traveler.model.dto.CommentDTO;
import com.soldiersoft.traveler.model.vo.CommentVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_comment(评论表)】的数据库操作Service
 * @createDate 2024-03-04 15:12:12
 */
public interface CommentService extends IService<Comment> {

    Page<CommentDTO> getComments(Long current, Long size);

    Page<CommentDTO> getCommentsByPostId(Long postId, Long current, Long size);

    String completeDeleteComment(Long commentId);

    String publishComment(CommentVO commentVO, String username);
}
