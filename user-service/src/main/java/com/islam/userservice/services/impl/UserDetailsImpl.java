package com.islam.userservice.services.impl;

import com.islam.userservice.converter.TempConverter;
import com.islam.userservice.dto.UserDTO;
import com.islam.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private TempConverter converter;


    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDTO userDTO = userService.getUserByEmail(username);
        return converter.userDtoToEntity(userDTO);
    }
}
