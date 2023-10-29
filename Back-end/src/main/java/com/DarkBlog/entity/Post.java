package com.DarkBlog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Type;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@Table(name="posts")
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    @Column(name = "created_at")
    private Date createdAt = new Date();
    @Lob
    private String text;
    private Integer point=0;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false ,referencedColumnName = "user_id")
    private User userId;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude

    private List<UpVote> upVotes=new ArrayList<>();

}
