package com.github.zcmee.komputronik;

import com.github.zcmee.komputronik.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserAuthentication implements UserDetails {

    private static final List<SimpleGrantedAuthority> USER_OR = Collections.singletonList(new SimpleGrantedAuthority("USER_OR"));

    private static final List<SimpleGrantedAuthority> USER_OPL = Collections.singletonList(new SimpleGrantedAuthority("USER_OPL"));

    private final User user;

    private final List<? extends GrantedAuthority> authorities;

    public UserAuthentication(User user) {
        this.user = user;
        switch (user.getAccountType()) {
            case COMP_INSTALATOR:
                authorities = USER_OR;
                break;
            case COMP_OPL:
                authorities = USER_OPL;
                break;
            default:
                authorities = Collections.emptyList();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

}

