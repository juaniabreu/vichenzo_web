package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.Role;
import com.example.vichenzobackend.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    public RoleService() {
    }

    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return this.roleRepository.findById(id);
    }

    public Role save(Role role) {
        return (Role)this.roleRepository.save(role);
    }

    public void deleteById(Long id) {
        this.roleRepository.deleteById(id);
    }

    public Role update(Role role) {
        return (Role)this.roleRepository.save(role);
    }
}
