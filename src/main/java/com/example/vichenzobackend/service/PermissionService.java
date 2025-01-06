package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.Permission;
import com.example.vichenzobackend.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionRepository permissionRepository;

    public PermissionService() {
    }

    public List<Permission> findAll() {
        return this.permissionRepository.findAll();
    }

    public Optional<Permission> findById(Long id) {
        return this.permissionRepository.findById(id);
    }

    public Permission save(Permission permission) {
        return (Permission)this.permissionRepository.save(permission);
    }

    public void deleteById(Long id) {
        this.permissionRepository.deleteById(id);
    }

    public Permission update(Permission permission) {
        return (Permission)this.permissionRepository.save(permission);
    }
}