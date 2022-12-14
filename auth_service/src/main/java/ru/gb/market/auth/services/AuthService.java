package ru.gb.market.auth.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.gb.market.api.dto.AuthRequest;
import ru.gb.market.api.dto.AuthResponse;
import ru.gb.market.api.exceptions.MarketError;
import ru.gb.market.auth.configs.JwtTokenUtil;
import ru.gb.market.auth.entities.PrivilegeEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    public ResponseEntity<?> generateAuthToken(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password!", "401"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        Long userId = userService.pullUserId(authRequest.getUsername());

        Collection <PrivilegeEntity> privilegeEntities = new ArrayList<>();
        for (GrantedAuthority role :userDetails.getAuthorities()){
            privilegeEntities.addAll(roleService.getPrivilegesByRoles(role.getAuthority()));
        }
        List<String> privilegesName = new ArrayList<>();
        for (PrivilegeEntity privilege: privilegeEntities){
            privilegesName.add(privilege.getName());
        }
        String token = jwtTokenUtil.generateToken(userDetails, userId, privilegesName);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
