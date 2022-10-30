package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.entities.Privilege;
import ru.gb.market.entities.Role;
import ru.gb.market.entities.User;
import ru.gb.market.repositories.UserRepository;
import ru.gb.market.utils.BCPassEncoder;
import ru.gb.market.utils.RoleGenerator;


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


    //    @Secured({"ROLE_ADMIN", "ROLE_USER"})
//    @PreAuthorize("USER")
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void saveUser(User user) {
        for (User items : userRepository.findAll()) {
            if (items.getUsername().equals(user.getUsername())) {
                log.error("User with name " + user.getUsername() + " is already exist!!!");
                throw new UsernameNotFoundException("User with name " + user.getUsername() + " is already exist!!!");
            }
        }

        String encryptedPassword = passEncoder.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles(roleGenerator.addOneRole("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dataUser = findByUsername(username)
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
