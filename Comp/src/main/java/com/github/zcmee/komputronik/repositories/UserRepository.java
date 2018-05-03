package com.github.zcmee.komputronik.repositories;

import com.github.zcmee.komputronik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(Long id);
    User findByLogin(String login);
}
