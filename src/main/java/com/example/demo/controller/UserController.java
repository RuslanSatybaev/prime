package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody User user,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg;
            List<FieldError> errors = bindingResult.getFieldErrors();
            errMsg = errors.stream()
                    .map(error -> error.getField() + " - " + error.getDefaultMessage() + ";")
                    .collect(Collectors.joining());
            throw new RuntimeException(errMsg);
        }
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.getUser(id);
        userService.deleteUser(id);
        return ResponseEntity.ok("User with id: " + id + " delete successfully!");
    }
}
