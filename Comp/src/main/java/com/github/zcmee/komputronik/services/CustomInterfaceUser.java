package com.github.zcmee.komputronik.services;

import com.github.zcmee.komputronik.entities.User;

public interface CustomInterfaceUser {
    User findByLogin(String login);
}
