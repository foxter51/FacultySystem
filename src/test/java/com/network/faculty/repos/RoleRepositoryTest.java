package com.network.faculty.repos;

import com.network.faculty.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTest {
    @Autowired
    RoleRepository repo;

    @Test
    void testGetRoleByName(){
        Role role = repo.getRoleByName("Student");
        assertThat(role.getName()).isEqualTo("Student");
    }
}
