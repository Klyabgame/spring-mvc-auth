package com.springbootmvc.crudspringbootmvc.services;

import com.springbootmvc.crudspringbootmvc.entities.User;
import com.springbootmvc.crudspringbootmvc.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>)  this.repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        return this.repository.save(user);
    }
}
