package ru.gb.market.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.core.services.AdministrationService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdministrationController {
    private final AdministrationService administrationService;

    @GetMapping("/adminPoint")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> administrationPoint(@RequestHeader String roles) {
        return administrationService.adminCheck(roles);
    }

}
