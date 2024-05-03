package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Comment;
import com.soldiersoft.traveler.entity.Post;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.mapper.CommentMapper;
import com.soldiersoft.traveler.model.dto.CommentDTO;
import com.soldiersoft.traveler.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_comment(评论表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    private static MPJLambdaWrapper<Comment> getCommentMPJLambdaWrapper() {
        return new MPJLambdaWrapper<>(Comment.class)
                .selectAll(Comment.class)
                .selectAssociation(User.class, CommentDTO::getUser)
                .selectAssociation(Post.class, CommentDTO::getPost)
                .leftJoin(User.class, User::getId, Comment::getUserId)
                .leftJoin(Post.class, Post::getId, Comment::getPostId);
    }

    private static List<CommentDTO> processCommentDTOPage(Page<CommentDTO> commentDTOPage) {
        return commentDTOPage.getRecords().stream().peek(commentDTO -> {
            User user = commentDTO.getUser();
            user.setPassword(null);
            user.setIsDisable(null);
        }).toList();
    }

    @Override
    public Page<CommentDTO> getComments(Long current, Long size) {
        MPJLambdaWrapper<Comment> wrapper = getCommentMPJLambdaWrapper();
        Page<CommentDTO> commentDTOPage = commentMapper.selectJoinPage(new Page<>(current, size), CommentDTO.class, wrapper);
        List<CommentDTO> records = processCommentDTOPage(commentDTOPage);
        commentDTOPage.setRecords(records);
        return commentDTOPage;
    }

    @Override
    public Page<CommentDTO> getCommentsByPostId(Long postId, Long current, Long size) {
        MPJLambdaWrapper<Comment> wrapper = getCommentMPJLambdaWrapper();
        wrapper.eq(Comment::getPostId, postId);
        Page<CommentDTO> commentDTOPage = commentMapper.selectJoinPage(new Page<>(current, size), CommentDTO.class, wrapper);
        List<CommentDTO> records = processCommentDTOPage(commentDTOPage);
        commentDTOPage.setRecords(records);
        return commentDTOPage;
    }

    @Override
    public String completeDeleteComment(Long commentId) {
        return commentMapper.deleteById(commentId) > 0 ? "删除评论信息成功" : "删除评论信息失败";
    }
}




