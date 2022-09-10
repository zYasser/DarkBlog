package com.DarkBlog.control;

import com.DarkBlog.entity.Role;
import com.DarkBlog.error.DoesNotExistException;
import com.DarkBlog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/add-role")
    public ResponseEntity<String> addRole(@RequestBody Role role){
        roleService.addRole(role);
        return ResponseEntity.ok("Done");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteRole(@RequestParam Long id) throws DoesNotExistException {
        boolean result=roleService.deleteRole(id);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(400).body(false);
    }
}
