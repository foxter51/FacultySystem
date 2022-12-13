package com.network.faculty.service;

import com.network.faculty.entities.User;
import com.network.faculty.repos.RoleRepository;
import com.network.faculty.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    protected UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.getUserByEmail(email);
        if(user == null) throw  new UsernameNotFoundException("User not found!");
        return user;
    }

    public boolean saveUser(User user, String role){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setBlocked(false);
        user.addRole(roleRepo.getRoleByName(role));
        user.setEnabled(true);
        if(repo.getUserByEmail(user.getEmail()) == null){
            repo.save(user);
            return true;
        }
        return false;
    }

    public User getUserById(Long userId){
        return repo.getReferenceById(userId);
    }

    public List<User> getUsersList(){
        return repo.findAll();
    }
}
