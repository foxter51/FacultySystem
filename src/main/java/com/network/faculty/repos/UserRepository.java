package com.network.faculty.repos;

import com.network.faculty.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}
