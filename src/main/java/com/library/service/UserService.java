package com.library.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.library.model.User;
import com.library.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}