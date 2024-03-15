package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("id not found - " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream().distinct().collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
