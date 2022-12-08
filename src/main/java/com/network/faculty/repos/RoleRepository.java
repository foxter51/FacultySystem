package com.network.faculty.repos;

import com.network.faculty.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
}
