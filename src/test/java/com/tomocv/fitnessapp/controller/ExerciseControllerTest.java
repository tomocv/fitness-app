package com.tomocv.fitnessapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListExercise() throws Exception {
        this.mockMvc
                .perform(
                        get("/exercise/list")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("exercise/exercise-list"))
                .andExpect(model().attributeExists("addedExercises"));
    }

    @Test
    void testAddNewExercise() throws Exception {
        this.mockMvc
                .perform(
                        get("/exercise/new")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("exercise/exercise-form"))
                .andExpect(model().attributeExists("exercise"));
    }

    @Test
    void invalidExerciseSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/exercise/added")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("exercise/exercise-form"));
    }

    @Test
    @DirtiesContext
    void validExerciseSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/exercise/added")
                                .param("muscleGroup", "Test Muscle Group")
                                .param("exerciseName", "Test Exercise Name")
                                .param("numberOfReps", "10")
                                .param("weight", "50")
                                .param("description", "Test description")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("exercise/exercise-added"));
    }

    @Test
    void searchExerciseSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/exercise/search")
                                .param("muscleGroup", "Chest")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("exercise/search"))
                .andExpect(model().attributeExists("exerciseSet"))
                .andExpect(model().attribute("exerciseSet", hasSize(1)));
    }
}
