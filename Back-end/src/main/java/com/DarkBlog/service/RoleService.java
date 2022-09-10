package com.DarkBlog.service;

import com.DarkBlog.entity.Role;
import com.DarkBlog.error.DoesNotExistException;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    boolean deleteRole(Long id) throws DoesNotExistException;
    List<Role> findAllRole() throws DoesNotExistException;
}
