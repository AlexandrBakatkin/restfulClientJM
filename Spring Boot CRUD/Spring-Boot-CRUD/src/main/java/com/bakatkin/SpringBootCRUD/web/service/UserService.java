package com.bakatkin.SpringBootCRUD.web.service;


import com.bakatkin.SpringBootCRUD.web.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> allUsers();

    void deleteUser(User user);

    void changeUser(Long id, String name, String surname, String address);

    void deleteById(Long id);

    User getById(Long id);

    User getByName(String s);
}