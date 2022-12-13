package com.network.faculty.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CustomUserDetailsServiceTest {

    @Test
    void loadUserByUsername() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUsersList() {
    }

    @Test
    void blockUserById() {
    }

    @Test
    void unblockUserById() {
    }

    @Test
    void deleteUserById() {
    }
}