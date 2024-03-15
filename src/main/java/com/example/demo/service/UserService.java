package com.example.demo.service;


import com.example.demo.entity.User;

import java.util.List;

public interface UserService {


    void saveUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);
}
