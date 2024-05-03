package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Post;
import com.soldiersoft.traveler.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String content;
    private User user;
    private Post post;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
