package com.islam.userservice.services.impl;

import com.islam.userservice.converter.TempConverter;
import com.islam.userservice.dto.UserDTO;
import com.islam.userservice.entity.UserEntity;
import com.islam.userservice.exceptions.InstanceUndefinedException;
import com.islam.userservice.repository.UserRepository;
import com.islam.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TempConverter converter;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Optional<Authentication> authenticateUser(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
        Optional<UserEntity> userOptional = userRepository.findByEmail(username);
        return userOptional.flatMap(userEntity -> {
            try {
                Authentication authentication = authenticationManager.authenticate(auth);
                return Optional.of(authentication);
            } catch (AuthenticationException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> allUsers = userRepository.findAll();
        List<UserDTO> returnUsers = new ArrayList<>();

        for (UserEntity userEntity : allUsers) {
            returnUsers.add(converter.userEntityToDto(userEntity));
        }
        return returnUsers;
    }

    @Override
    public UserDTO getUserById(int id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return converter.userEntityToDto(userOptional.get());
        } else {
            throw new InstanceUndefinedException(new Error("User with ID " + id + " not found"));
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return converter.userEntityToDto(userOptional.get());
        } else {
            throw new InstanceUndefinedException(new Error("User with email " + email + " not found"));
        }
    }

    @Override
    public UserDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            String currentUserName = auth.getName();
            return getUserByEmail(currentUserName);
        } else {
            throw new InstanceUndefinedException(new Error("Current user not found"));
        }
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO user) {
        UserEntity userEntity = converter.userDtoToEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return converter.userEntityToDto(savedUserEntity);
    }


}
