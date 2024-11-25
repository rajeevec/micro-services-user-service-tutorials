package com.user.service.service;

import com.user.service.entities.User;

import java.util.List;

public interface UserService {

    public User addUser(User user);

    public List<User> getAllUsers();

    public User getUserByUserId(Long userId);

    public User deleteUserByUserId(Long userId);
}
