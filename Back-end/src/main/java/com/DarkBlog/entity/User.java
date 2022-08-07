package com.DarkBlog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_Sequence", sequenceName = "user_Sequence", allocationSize = 0, initialValue = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private int points;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "created_at")
    private Date createdAt = new Date();

}