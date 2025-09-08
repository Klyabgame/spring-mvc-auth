package com.springbootmvc.crudspringbootmvc.services;

import com.springbootmvc.crudspringbootmvc.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);

}
