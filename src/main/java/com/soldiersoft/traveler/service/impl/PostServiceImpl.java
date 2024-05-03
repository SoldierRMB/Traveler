package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Post;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.PostMapper;
import com.soldiersoft.traveler.model.dto.PostDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.PostVO;
import com.soldiersoft.traveler.service.PostService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_post(动态表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {
    private final PostMapper postMapper;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostMapper postMapper, UserService userService) {
        this.postMapper = postMapper;
        this.userService = userService;
    }

    @Override
    public Page<PostDTO> getPosts(Long current, Long size) {
        MPJLambdaWrapper<Post> wrapper = new MPJLambdaWrapper<>(Post.class)
                .selectAll(Post.class)
                .selectAssociation(User.class, PostDTO::getUser)
                .leftJoin(User.class, User::getId, Post::getUserId)
                .orderByDesc(Post::getCreateTime);
        Page<PostDTO> postDTOPage = postMapper.selectJoinPage(new Page<>(current, size), PostDTO.class, wrapper);
        List<PostDTO> records = postDTOPage.getRecords().stream().peek(postDTO -> {
            User user = postDTO.getUser();
            user.setPassword(null);
            user.setIsDisable(null);
            postDTO.setUser(user);
        }).toList();
        postDTOPage.setRecords(records);
        return postDTOPage;
    }

    @Override
    public String publishPost(PostVO postVO, String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        Post post = new Post();
        BeanUtils.copyProperties(postVO, post);
        post.setUserId(userDTO.getId());
        try {
            save(post);
            return "旅游动态发布成功";
        } catch (Exception e) {
            throw new BizException("旅游动态发布失败");
        }
    }

    @Override
    public String completeDeletePost(Long postId) {
        return postMapper.deleteById(postId) > 0 ? "旅游动态删除成功" : "旅游动态删除失败";
    }
}




