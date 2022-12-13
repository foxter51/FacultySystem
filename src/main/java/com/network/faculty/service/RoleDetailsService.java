package com.network.faculty.service;

import com.network.faculty.repos.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleDetailsService {
    protected final RoleRepository repo;
}
