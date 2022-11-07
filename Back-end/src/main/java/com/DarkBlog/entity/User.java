package com.DarkBlog.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;
    @Length(min = 3)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private int points=0;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(name = "created_at")
    private Date createdAt = new Date();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "users")
    private List<Role> roles = new ArrayList<>();
    @JsonBackReference

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude

    private List<UpVote> upVotes=new ArrayList<>();
}
