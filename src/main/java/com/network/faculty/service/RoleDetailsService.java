package com.network.faculty.service;

import com.network.faculty.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDetailsService {
    @Autowired
    protected RoleRepository repo;
}
