package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Comment;
import com.soldiersoft.traveler.entity.Post;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.CommentMapper;
import com.soldiersoft.traveler.model.dto.CommentDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.CommentVO;
import com.soldiersoft.traveler.service.CommentService;
import com.soldiersoft.traveler.service.PostService;
import com.soldiersoft.traveler.service.UserService;
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
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper, UserService userService, PostService postService) {
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.postService = postService;
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

    @Override
    public String publishComment(CommentVO commentVO, String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        return postService.getOptById(commentVO.getPostId())
                .map(post -> {
                    Comment comment = Comment.builder()
                            .content(commentVO.getContent())
                            .userId(userDTO.getId())
                            .postId(post.getId())
                            .build();
                    save(comment);
                    return "评论发布成功";
                }).orElseThrow(() -> new BizException("旅游动态不存在"));
    }
}




