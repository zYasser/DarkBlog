package com.DarkBlog.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpVoteForm {
    private Long userId;
    private Long postId;
    private Integer point;
}
