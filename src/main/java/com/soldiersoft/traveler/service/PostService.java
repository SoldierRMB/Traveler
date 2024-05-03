package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Post;
import com.soldiersoft.traveler.model.dto.PostDTO;
import com.soldiersoft.traveler.model.vo.PostVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_post(动态表)】的数据库操作Service
 * @createDate 2024-03-04 15:12:12
 */
public interface PostService extends IService<Post> {

    Page<PostDTO> getPosts(Long current, Long size);

    String publishPost(PostVO postVO, String username);

    String completeDeletePost(Long postId);
}
