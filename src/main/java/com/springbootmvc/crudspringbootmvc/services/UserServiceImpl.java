package com.springbootmvc.crudspringbootmvc.services;

import com.springbootmvc.crudspringbootmvc.entities.Role;
import com.springbootmvc.crudspringbootmvc.entities.User;
import com.springbootmvc.crudspringbootmvc.repositories.RoleRepository;
import com.springbootmvc.crudspringbootmvc.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>)  this.userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        Optional<Role> optionalRoleUser=roleRepository.findByName("ROLE_USER");
        List<Role> roles=new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if(user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);

        }
        user.setRoles(roles);

        String passwordEncoded=passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return this.userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
