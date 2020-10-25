package com.bakatkin.SpringBootCRUD.web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "roles")
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String role;

    @Transient
    @ManyToMany
    private Set<User> users;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String name) {
        this.role = name;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString(){
        return getRole();
    }
}