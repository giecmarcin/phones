package com.mmgiec.app.services;

import com.mmgiec.app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
