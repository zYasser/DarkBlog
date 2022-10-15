package com.DarkBlog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpVoteId implements Serializable {
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "user_id")
    private Long userId;

    @Override
    public int hashCode() {
        return Objects.hash(userId,postId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UpVoteId that = (UpVoteId) obj;
        return Objects.equals(postId, that.postId) &&
                Objects.equals(userId, that.userId);

    }
}
