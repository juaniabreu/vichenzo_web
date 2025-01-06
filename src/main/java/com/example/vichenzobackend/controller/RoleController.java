//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.vichenzobackend.controller;

import com.example.vichenzobackend.model.Permission;
import com.example.vichenzobackend.model.Role;
import com.example.vichenzobackend.service.IPermissionService;
import com.example.vichenzobackend.service.IRoleService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/roles"})
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permiService;

    public RoleController() {
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = this.roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = this.roleService.findById(id);
        return (ResponseEntity)role.map(ResponseEntity::ok).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Set<Permission> permiList = new HashSet();
        Iterator var4 = role.getPermissionsList().iterator();

        while(var4.hasNext()) {
            Permission per = (Permission)var4.next();
            Permission readPermission = (Permission)this.permiService.findById(per.getId()).orElse((Permission) null);
            if (readPermission != null) {
                permiList.add(readPermission);
            }
        }

        role.setPermissionsList(permiList);
        Role newRole = this.roleService.save(role);
        return ResponseEntity.ok(newRole);
    }
}
