package com.network.faculty.repos;

import com.network.faculty.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);

    @Modifying
    @Query("UPDATE User user SET user.blocked = 1 WHERE user.id = ?1")
    void blockById(Long id);

    @Modifying
    @Query("UPDATE User user SET user.blocked = 0 WHERE user.id = ?1")
    void unblockById(Long id);
}
