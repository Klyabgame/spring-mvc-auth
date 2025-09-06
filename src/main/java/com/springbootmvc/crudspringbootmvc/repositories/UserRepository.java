package com.springbootmvc.crudspringbootmvc.repositories;

import com.springbootmvc.crudspringbootmvc.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
