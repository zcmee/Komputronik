package com.github.zcmee.komputronik.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.zcmee.komputronik.entities.User;
import com.github.zcmee.komputronik.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService implements ServiceInterface<User>, CustomInterfaceUser {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return null;
    }
}
