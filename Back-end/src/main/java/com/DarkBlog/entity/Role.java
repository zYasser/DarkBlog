package com.DarkBlog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private List<User> users = new ArrayList<>();
}
