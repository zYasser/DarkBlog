package com.DarkBlog.repository;

import com.DarkBlog.entity.UpVote;
import com.DarkBlog.entity.UpVoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpVoteRepository extends JpaRepository<UpVote , UpVoteId> {
}
