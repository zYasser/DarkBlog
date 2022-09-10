package com.DarkBlog.form;

import com.DarkBlog.entity.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostForm
{
    private Post post;
    private Long UserId;
}
