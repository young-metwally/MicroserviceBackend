package com.islam.userservice.utils;

import com.islam.userservice.entity.RoleEntity;
import com.islam.userservice.entity.UserEntity;
import com.islam.userservice.exceptions.InstanceUndefinedException;
import com.islam.userservice.repository.RoleRepository;
import com.islam.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadData {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoadData(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        addRolesData();
        addAdminsData();
    }

    public void addRolesData() {
        RoleEntity roleAdmin = new RoleEntity("ROLE_ADMIN");
        RoleEntity roleUser = new RoleEntity("ROLE_USER");
        if (roleRepository.findByRole("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(roleAdmin);
        }
        if (roleRepository.findByRole("ROLE_USER").isEmpty()) {
            roleRepository.save(roleUser);
        }
    }

    public void addAdminsData() {
        RoleEntity roleAdmin = roleRepository.findByRole("ROLE_ADMIN")
                .orElseThrow(() -> new InstanceUndefinedException(new Error("ROLE_ADMIN not found.")));
        if (userRepository.findByEmail("admin@islam.com").isEmpty()) {
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(roleAdmin);
            UserEntity userEntity = new UserEntity("admin@islam.com", "2244", "Islam", "Metwally", (byte) 1);
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
            userEntity.setRoles(roles);
            try {
                UserEntity storedAdmin = userRepository.save(userEntity);
                roleAdmin.setUsers(List.of(storedAdmin));
                roleRepository.save(roleAdmin);
            } catch (Exception e) {
                System.out.println("Admin already added.");
            }
        } else {
            System.out.println("Admin already added.");
        }
    }
}