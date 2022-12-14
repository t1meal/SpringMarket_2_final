package ru.gb.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.AuthRequest;
import ru.gb.market.auth.services.AuthService;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping ("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest){
        return authService.generateAuthToken(authRequest);
    }

}
