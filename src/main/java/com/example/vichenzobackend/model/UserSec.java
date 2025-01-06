package com.example.vichenzobackend.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users"
)
public class UserSec {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            unique = true
    )
    private String username;
    private String password;
    private boolean enabled = true;
    private boolean accountNotExpired = true;
    private boolean accountNotLocked = true;
    private boolean credentialNotExpired = true;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id"
            )}
    )
    private Set<Role> rolesList = new HashSet();

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isAccountNotExpired() {
        return this.accountNotExpired;
    }

    public boolean isAccountNotLocked() {
        return this.accountNotLocked;
    }

    public boolean isCredentialNotExpired() {
        return this.credentialNotExpired;
    }

    public Set<Role> getRolesList() {
        return this.rolesList;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNotExpired(final boolean accountNotExpired) {
        this.accountNotExpired = accountNotExpired;
    }

    public void setAccountNotLocked(final boolean accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    public void setCredentialNotExpired(final boolean credentialNotExpired) {
        this.credentialNotExpired = credentialNotExpired;
    }

    public void setRolesList(final Set<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public UserSec(final Long id, final String username, final String password, final boolean enabled, final boolean accountNotExpired, final boolean accountNotLocked, final boolean credentialNotExpired, final Set<Role> rolesList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNotExpired = accountNotExpired;
        this.accountNotLocked = accountNotLocked;
        this.credentialNotExpired = credentialNotExpired;
        this.rolesList = rolesList;
    }

    public UserSec() {
    }
}