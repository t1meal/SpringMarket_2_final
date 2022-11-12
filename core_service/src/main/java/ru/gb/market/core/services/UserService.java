package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.NewUserDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.entities.Privilege;
import ru.gb.market.core.entities.Role;
import ru.gb.market.core.entities.UserEntity;
import ru.gb.market.core.integrations.CartServiceIntegration;
import ru.gb.market.core.mappers.UserMapper;
import ru.gb.market.core.repositories.UserRepository;
import ru.gb.market.core.utils.BCPassEncoder;
import ru.gb.market.core.utils.RoleGenerator;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCPassEncoder passEncoder;

    private final RoleGenerator roleGenerator;

    private final UserMapper userMapper;

    private final CartServiceIntegration cartServiceIntegration;


    //    @Secured({"ROLE_ADMIN", "ROLE_USER"})
//    @PreAuthorize("USER")
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Long pullUserId(String userName) {
        return findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User with name" + userName + "not found!"))
                .getId();
    }

    @Transactional
    public void createUser (NewUserDto user) {
        for (UserEntity items : userRepository.findAll()) {
            if (items.getUsername().equals(user.getUsername())) {
                log.error("User with name " + user.getUsername() + " is already exist!!!");
                throw new UsernameNotFoundException("User with name " + user.getUsername() + " is already exist!!!");
            }
        }
        UserEntity newUser = userMapper.dtoToEntity(user);
        String encryptedPassword = passEncoder.getPasswordEncoder().encode(user.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setRoles(roleGenerator.addOneRole("ROLE_USER"));
        userRepository.save(newUser);
        cartServiceIntegration.createCart(newUser.getId());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity dataUser = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails
                .User(dataUser.getUsername(), dataUser.getPassword(), getAuthorities(dataUser.getRoles()));
    }

    //    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<String> rolesAndPrivilegeList = new ArrayList<>();
        List<Privilege> privilegeList = new ArrayList<>();

        for (Role item : roles) {
            rolesAndPrivilegeList.add(item.getName());
            privilegeList.addAll(item.getPrivileges());
        }
        for (Privilege item : privilegeList) {
            rolesAndPrivilegeList.add(item.getName());
        }
        return rolesAndPrivilegeList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
