package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "Username is empty!")
    @Length(min = 2, max = 50, message = "Incorrect username length!")
    private String username;

    @NotNull(message = "Password is empty!")
    @Length(min = 3, max = 50, message = "Incorrect password length!")
    private String password;

    @Length(min = 10, max = 30, message = "Incorrect email!")
    private String email;

    public UserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
