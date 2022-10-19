package com.DarkBlog.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpVote implements Serializable {
    @EmbeddedId
    private UpVoteId upVoteId;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ToString.Exclude

    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Post post;
    private Integer points;
}
