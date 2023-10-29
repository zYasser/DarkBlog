package com.DarkBlog.form;

import com.DarkBlog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostForm
{
    private Long user_id;
    private Post post;
}
