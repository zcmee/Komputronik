package com.github.zcmee.komputronik.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.github.zcmee.komputronik.AuthorizationUser;
import com.github.zcmee.komputronik.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        User currentUser = userService.findByLogin(login);
        if (currentUser != null) { return new AuthorizationUser(currentUser); }
        throw new UsernameNotFoundException("User not found for login: " + login);
    }
}
