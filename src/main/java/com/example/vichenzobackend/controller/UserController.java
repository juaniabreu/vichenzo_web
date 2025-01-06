package com.example.vichenzobackend.controller;


import com.example.vichenzobackend.model.Role;
import com.example.vichenzobackend.model.UserSec;
import com.example.vichenzobackend.service.IRoleService;
import com.example.vichenzobackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping({"/api/users"})
@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin({"*"})
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    public UserController() {
    }

    @GetMapping
    public ResponseEntity<List<UserSec>> getAllUsers() {
        List<UserSec> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {
        Optional<UserSec> user = this.userService.findById(id);
        return (ResponseEntity)user.map(ResponseEntity::ok).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec) {
        Set<Role> roleList = new HashSet();
        userSec.setPassword(this.userService.encriptPassword(userSec.getPassword()));
        Iterator var4 = userSec.getRolesList().iterator();

        while(var4.hasNext()) {
            Role role = (Role)var4.next();
            Role readRole = (Role)this.roleService.findById(role.getId()).orElse((Role) null);
            if (readRole != null) {
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {
            userSec.setRolesList(roleList);
            UserSec newUser = this.userService.save(userSec);
            return ResponseEntity.ok(newUser);
        } else {
            return null;
        }
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<UserSec> deleteUser(@PathVariable Long id) {
        UserSec user = (UserSec)this.userService.findById(id).get();
        user.setEnabled(false);
        this.userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping({"/alta/{id}"})
    public ResponseEntity<UserSec> updateUser(@PathVariable Long id) {
        UserSec user = (UserSec)this.userService.findById(id).get();
        user.setEnabled(true);
        this.userService.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping({"/buscar"})
    public ResponseEntity<UserSec> buscarUser(@RequestBody String username) {
        System.out.println(username);
        Optional<UserSec> user = this.userService.findEntityByUsername(username);
        return user.isPresent() ? ResponseEntity.ok((UserSec)user.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping({"/who"})
    public ResponseEntity<String> who() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getName());
        UserSec user = (UserSec)auth.getPrincipal();
        System.out.println(user.getUsername());
        return new ResponseEntity("username", HttpStatus.OK);
    }
}
