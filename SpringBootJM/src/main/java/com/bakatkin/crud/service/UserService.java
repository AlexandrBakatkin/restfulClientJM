package com.bakatkin.crud.service;

import com.bakatkin.crud.model.Role;
import com.bakatkin.crud.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void add(User user);

    List<User> allUsers();

    void deleteUser(User user);

    void changeUser(Long id, String name, String surname, String address, Set<Role> roles);

    void deleteById(Long id);

    User getById(Long id);

    User getByName(String s);

    public List<Role> getAllRoles();
}
