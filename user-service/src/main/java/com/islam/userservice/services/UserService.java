package com.islam.userservice.services;

import com.islam.userservice.dto.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Authentication> authenticateUser(String username, String password);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    UserDTO getUserByEmail(String email);
    UserDTO getCurrentUser();
    UserDTO saveUser(UserDTO user);
}
