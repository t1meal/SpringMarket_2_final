package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.market.api.dto.AuthResponse;
import ru.gb.market.core.exceptions.AccessDeniedException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdministrationService {

    public ResponseEntity<?> adminCheck(String roles) {
        log.warn(roles);
        if (roles.equals("[ROLE_ADMIN]")){
            return ResponseEntity.ok(new AuthResponse("Hi, admin. Access is allowed!!!"));
        } else {
            throw new AccessDeniedException("Access denied! Back");
        }

    }
}
