package com.example.vichenzobackend.service;

import com.example.vichenzobackend.model.UserSec;
import com.example.vichenzobackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    public UserService() {
    }

    public List<UserSec> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<UserSec> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public UserSec save(UserSec userSec) {
        return (UserSec)this.userRepository.save(userSec);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public void update(UserSec userSec) {
        this.save(userSec);
    }

    public String encriptPassword(String password) {
        return (new BCryptPasswordEncoder()).encode(password);
    }

    public Optional<UserSec> findEntityByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username);
    }
}