package com.tomocv.fitnessapp;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFormLoginAdmin() throws Exception {
        this.mockMvc
                .perform(formLogin("/login").user("username", "admin").password("password", "adminpass"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void testFormLoginWrong() throws Exception {
        this.mockMvc
                .perform(formLogin("/login").user("username", "ivica").password("password", "nopass"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

}
