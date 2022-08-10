package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class AuthRequest {
    private String username;
    private String password;
}
