package com.DarkBlog.service;

import com.DarkBlog.entity.Role;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        Optional<Role> temp = roleRepository.findByName(role.getName());
        if(temp.isPresent())
            throw new DuplicateKeyException("Duplicate");
        roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(Long id) throws DoesNotExistException {
        Optional<Role> role= Optional.ofNullable(roleRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Role with that id doesn't exist")));
        roleRepository.delete(role.get());
        return true ;
    }

    @Override
    public List<Role> findAllRole() throws DoesNotExistException {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty())
            throw new DoesNotExistException("there is no roles available right now");
        return roles;
    }
}
