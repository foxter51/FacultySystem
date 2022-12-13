package com.network.faculty.service;

import com.network.faculty.entities.User;
import com.network.faculty.repos.RoleRepository;
import com.network.faculty.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsServiceTest {

    private final UserRepository userRepo = Mockito.mock(UserRepository.class);
    private final RoleRepository roleRepo = Mockito.mock(RoleRepository.class);
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    void setUserDetailsService(){
        RoleDetailsService roleDetailsService = new RoleDetailsService(roleRepo);
        userDetailsService = new CustomUserDetailsService(userRepo, roleDetailsService);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("test"));
        user.setFirstName("Ihor");
        user.setLastName("Fedorchenko");
        user.setBlocked(false);
        user.setEnabled(true);
        boolean success = userDetailsService.saveUser(user, "Student");
        System.out.println(success);
        assertTrue(success);
    }

    @Test
    void testGetUserById() {
//        userDetailsService.getUserById(3L);
//        verify(userRepo.getReferenceById(3L));
    }

    @Test
    void testGetUsersList() {
    }

    @Test
    void testBlockUserById() {
    }

    @Test
    void testUnblockUserById() {
    }

    @Test
    void testDeleteUserById() {
    }
}