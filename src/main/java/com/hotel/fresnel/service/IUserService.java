package com.hotel.fresnel.service;

import com.hotel.fresnel.model.User;

import java.util.List;



public interface IUserService {
    User registerUser(User user);
    User saveUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);

    User getUserById(Long id);
}
