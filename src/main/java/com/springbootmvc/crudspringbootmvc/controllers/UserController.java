package com.springbootmvc.crudspringbootmvc.controllers;

import com.springbootmvc.crudspringbootmvc.entities.User;
import com.springbootmvc.crudspringbootmvc.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> getUsers() {

        return this.userService.findAll();

    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasFieldErrors()){
            return
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( this.userService.save(user));


    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(),"El Campo" + err.getField() + " " + err.getDefaultMessage());
        });

        return  ResponseEntity.badRequest().body(errors);
    }


}
