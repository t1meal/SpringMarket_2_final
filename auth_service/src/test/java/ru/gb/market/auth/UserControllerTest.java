package ru.gb.market.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getUserByUserName() throws Exception {

        mvc
                .perform(
                        get("/api/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("userName", "admin")

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role", is("ROLE_ADMIN")));
    }

}
