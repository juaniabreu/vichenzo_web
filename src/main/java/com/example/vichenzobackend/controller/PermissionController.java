package com.example.vichenzobackend.controller;

import com.example.vichenzobackend.model.Permission;
import com.example.vichenzobackend.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/permissions"})
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    public PermissionController() {
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = this.permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission = this.permissionService.findById(id);
        return (ResponseEntity)permission.map(ResponseEntity::ok).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission newPermission = this.permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }
}