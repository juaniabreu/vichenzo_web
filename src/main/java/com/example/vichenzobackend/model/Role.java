package com.example.vichenzobackend.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "roles"
)
public class Role {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String role;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "roles_permissions",
            joinColumns = {@JoinColumn(
                    name = "role_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "permission_id"
            )}
    )
    private Set<Permission> permissionsList = new HashSet();

    public Long getId() {
        return this.id;
    }

    public String getRole() {
        return this.role;
    }

    public Set<Permission> getPermissionsList() {
        return this.permissionsList;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public void setPermissionsList(final Set<Permission> permissionsList) {
        this.permissionsList = permissionsList;
    }

    public Role(final Long id, final String role, final Set<Permission> permissionsList) {
        this.id = id;
        this.role = role;
        this.permissionsList = permissionsList;
    }

    public Role() {
    }
}