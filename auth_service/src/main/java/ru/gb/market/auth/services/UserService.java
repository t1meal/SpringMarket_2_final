package ru.gb.market.auth.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.NewUserDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.auth.configs.RoleGenerator;
import ru.gb.market.auth.entities.RoleEntity;
import ru.gb.market.auth.entities.UserEntity;
import ru.gb.market.auth.mappers.UserMapper;
import ru.gb.market.auth.repositories.UserRepository;


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
    private final BCryptPasswordEncoder passEncoder;

    private final RoleGenerator roleGenerator;

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
    public void createUser(NewUserDto newUserDto) {
        for (UserEntity items : userRepository.findAll()) {
            if (items.getUsername().equals(newUserDto.getUsername())) {
                log.error("User with name " + newUserDto.getUsername() + " is already exist!!!");
                throw new UsernameNotFoundException("User with name " + newUserDto.getUsername() + " is already exist!!!");
            }
        }

        UserEntity userEntity = UserEntity.builder()
                .username(newUserDto.getUsername())
                .password(passEncoder.encode(newUserDto.getPassword()))
                .gender(newUserDto.getGender())
                .age(newUserDto.getAge())
                .email(newUserDto.getEmail())
                .phone(newUserDto.getPhone())
                .build();

        userEntity.setRoles(roleGenerator.addOneRole("ROLE_USER"));
        userRepository.save(userEntity);
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
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleEntity> roles) {
        List<String> rolesList = new ArrayList<>();
        for (RoleEntity role : roles) {
            rolesList.add(role.getName());
        }
        return rolesList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


}
