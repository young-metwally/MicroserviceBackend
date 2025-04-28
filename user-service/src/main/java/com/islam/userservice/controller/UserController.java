package com.islam.userservice.controller;

import com.islam.userservice.converter.TempConverter;
import com.islam.userservice.dto.UserDTO;
import com.islam.userservice.entity.UserEntity;
import com.islam.userservice.services.UserService;
import com.islam.userservice.utils.AuthenticationRequest;
import com.islam.userservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TempConverter tempConverter;
    @Autowired
    private JwtUtil jwtUtil;

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/id/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId) {
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser() {
        UserDTO user = userService.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws CredentialNotFoundException {
        Optional<Authentication> authenticationOptional = userService.authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        if (authenticationOptional.isEmpty()) {
            throw new CredentialNotFoundException("Invalid user name or password.");
        }
        UserDTO authUser = userService.getUserByEmail(authenticationOptional.get().getName());
        UserEntity authUserEntity = tempConverter.userDtoToEntity(authUser);
        String jwtToken = jwtUtil.generateToken(authUserEntity);
        authUser = tempConverter.userEntityToDto(authUserEntity);
        authUser.setAuthToken(jwtToken);
        return new ResponseEntity<>(authUser, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}
