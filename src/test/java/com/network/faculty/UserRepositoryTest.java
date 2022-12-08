package com.network.faculty;

import com.network.faculty.entities.User;
import com.network.faculty.repos.RoleRepository;
import com.network.faculty.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void testCreateUser(){
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("test"));
        user.setFirstName("Ihor");
        user.setLastName("Fedorchenko");
        user.addRole(roleRepo.getRoleByName("Student"));
        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void testFindUserByEmail(){
        String email = "test@gmail.com";
        User foundUser = repo.getUserByEmail(email);
        assertThat(email).isEqualTo(foundUser.getEmail());
    }
}
