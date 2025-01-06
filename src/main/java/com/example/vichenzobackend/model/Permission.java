package com.example.vichenzobackend.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "permissions"
)
public class Permission {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            unique = true,
            nullable = false
    )
    private String permissionName;

    public Long getId() {
        return this.id;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPermissionName(final String permissionName) {
        this.permissionName = permissionName;
    }

    public Permission(final Long id, final String permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    public Permission() {
    }
}
