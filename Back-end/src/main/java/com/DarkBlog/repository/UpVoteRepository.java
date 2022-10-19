package com.DarkBlog.repository;

import com.DarkBlog.entity.UpVote;
import com.DarkBlog.entity.UpVoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UpVoteRepository extends JpaRepository<UpVote , UpVoteId> {
    @Query(value = "SELECT * FROM up_vote where user_id=:userId AND post_id=:postId",nativeQuery = true)
    Optional<UpVote>findByPostIdAndUser(@Param("userId")Long userId,@Param("postId") Long postId);
}
