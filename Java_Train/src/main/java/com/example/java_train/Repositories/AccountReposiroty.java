package com.example.java_train.Repositories;

import com.example.java_train.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountReposiroty extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
}
