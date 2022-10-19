package com.DarkBlog.service;

import com.DarkBlog.error.DoesNotExistException;

public interface UpVoteService {
    void vote(Long userId, Long postId, int point) throws DoesNotExistException;
}
