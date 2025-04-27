package com.islam.userservice.converter;

import com.islam.userservice.dto.RoleDTO;
import com.islam.userservice.dto.UserDTO;
import com.islam.userservice.entity.RoleEntity;
import com.islam.userservice.entity.UserEntity;
import com.islam.userservice.repository.RoleRepository;
import com.islam.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TempConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO roleEntityToDto(RoleEntity roleentity) {
        RoleDTO returnValue = modelMapper.map(roleentity, RoleDTO.class);
        Optional<List<UserEntity>> usersOptional = Optional.ofNullable(roleentity.getUsers());
        List<Integer> usersIds = new ArrayList<>();
        if (usersOptional.isPresent()) {
            List<UserEntity> users = usersOptional.get();
            for (UserEntity user : users) {
                usersIds.add(user.getId());
            }
        }
        returnValue.setUsersIds(usersIds);
        return returnValue;
    }

    public RoleEntity roleDtoToEntity(RoleDTO roledto) {
        RoleEntity returnValue = modelMapper.map(roledto, RoleEntity.class);
        Optional<List<Integer>> usersIdsOptional = Optional.ofNullable(roledto.getUsersIds());
        List<UserEntity> users = new ArrayList<>();
        if (usersIdsOptional.isPresent()) {
            List<Integer> usersIds = usersIdsOptional.get();
            for (Integer userId : usersIds) {
                UserEntity user = userRepository.findById(userId).orElse(null);
                if (user != null) {
                    users.add(user);
                }
            }
        }
        returnValue.setUsers(users);
        return returnValue;
    }

    public UserDTO userEntityToDto(UserEntity userentity) {
        UserDTO returnValue = modelMapper.map(userentity, UserDTO.class);

        Optional<Byte> enabledOptional = Optional.ofNullable(userentity.getEnabled());
        enabledOptional.ifPresent((enabled) -> returnValue.setEnabled((short) enabled));

        Optional<List<RoleEntity>> rolesOptional = Optional.ofNullable(userentity.getRoles());
        List<Integer> rolesIds = new ArrayList<>();
        if (rolesOptional.isPresent()) {
            List<RoleEntity> roles = rolesOptional.get();
            for (RoleEntity role : roles) {
                rolesIds.add(role.getId());
            }
        }
        returnValue.setRolesIds(rolesIds);
        return returnValue;
    }

    public UserEntity userDtoToEntity(UserDTO userdto) {
        UserEntity returnValue = modelMapper.map(userdto, UserEntity.class);
        Optional<Short> enabledOptional = Optional.ofNullable(userdto.getEnabled());
        if (enabledOptional.isPresent()) {
            Short enabled = enabledOptional.get();
            returnValue.setEnabled(enabled.byteValue());
        }
        Optional<List<Integer>> rolesIdsIdsOptional = Optional.ofNullable(userdto.getRolesIds());
        List<RoleEntity> roles = new ArrayList<>();
        if (rolesIdsIdsOptional.isPresent()) {
            List<Integer> rolesIds = rolesIdsIdsOptional.get();
            for (Integer roleId : rolesIds) {
                RoleEntity role = roleRepository.findById(roleId).orElse(null);
                if (role != null) {
                    roles.add(role);
                }
            }
        }
        returnValue.setRoles(roles);
        return returnValue;

    }
}
