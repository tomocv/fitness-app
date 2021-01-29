package com.tomocv.fitnessapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomeScreen() throws Exception{
        mockMvc.perform(get("/home")
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("home/home-screen"));
    }

    @Test
    public void testHomeScreenForward() throws Exception{
        mockMvc.perform(get("/")
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/home"));
    }
}
