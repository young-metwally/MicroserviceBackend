package com.islam.userservice.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 50)
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    @Transient
    private List<UserEntity> users;

    public RoleEntity(String role) {
        this.role = role;
    }

    public RoleEntity() {
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }


}
