package com.github.zcmee.komputronik;

import com.github.zcmee.komputronik.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuthorizationUser implements UserDetails {

    private final String password;
    private final String login;

    private final List<? extends GrantedAuthority> authorities;

    public AuthorizationUser(User user) {
        System.out.println(user);
        password = user.getPassword();
        login = user.getLogin();

        switch(user.getAccountType()) {
            case COMP_INSTALATOR :
                authorities = Collections.singletonList(new SimpleGrantedAuthority("USER_OR"));
            break;
            case COMP_OPL :
                authorities = Collections.singletonList(new SimpleGrantedAuthority("USER_OPL"));
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
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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
}

