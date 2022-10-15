package com.DarkBlog.repository;

import com.DarkBlog.entity.Post;
import com.DarkBlog.entity.UpVote;
import com.DarkBlog.entity.UpVoteId;
import com.DarkBlog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UpVoteRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UpVoteRepository upRepository;

    @Test
    void should_save_theUpVote() {
        Optional<User> user = userRepository.findById(3L);
        Optional<Post> post = postRepository.findById(104L);
        UpVoteId upVoteId = UpVoteId.builder().postId(104L).userId(3L).build();
        UpVote attribute = UpVote.builder().points(1).post(post.get()).user(user.get()).upVoteId(upVoteId).build();
        upRepository.save(attribute);
        List<UpVote> upVotes=upRepository.findAll();
        assertThat(upVotes).hasSize(1);
    }
}