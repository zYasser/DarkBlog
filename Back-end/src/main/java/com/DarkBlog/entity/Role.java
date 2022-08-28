package com.DarkBlog.entity;

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
    private String name;
    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles" , joinColumns = {
            @JoinColumn(name = "role_id" ,referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")
    })
    private List<User> users=new ArrayList<>();
}
