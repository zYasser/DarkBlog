package com.DarkBlog.service;

import com.DarkBlog.entity.Post;
import com.DarkBlog.entity.UpVote;
import com.DarkBlog.entity.UpVoteId;
import com.DarkBlog.entity.User;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.repository.PostRepository;
import com.DarkBlog.repository.UpVoteRepository;
import com.DarkBlog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class UpVoteServiceImpl implements UpVoteService {
    @Autowired
    private UpVoteRepository upVoteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public void vote(Long userId, Long postId, int value) throws DoesNotExistException {
        log.info("currently in the service, process the request from userId {} to upvote post {} ",userId,postId );
        Optional<UpVote> existedVote=upVoteRepository.findByPostIdAndUser(userId,postId);
        Post post=postRepository.findById(postId).orElseThrow(()->new DoesNotExistException("Post doesn't exist"));
        User user=userRepository.findById(userId).orElseThrow(()->new DoesNotExistException("User doesn't exist"));
        UpVoteId voteId=UpVoteId.builder().userId(userId).postId(postId).build();
        if(existedVote.isEmpty()){
            upVoteRepository.save(UpVote.builder().points(value).post(post).user(user).upVoteId(voteId).build());
            post.setPoint(post.getPoint()+value);

        }
        else if(existedVote.get().getPoints()==value){
            upVoteRepository.delete(existedVote.get());
            post.setPoint(value==1 ? post.getPoint()-1  :post.getPoint()+1);
        }else{
            existedVote.get().setPoints(value);
            upVoteRepository.save(existedVote.get());
            post.setPoint(post.getPoint()+(value*2));
        }
        postRepository.save(post);


    }

}
