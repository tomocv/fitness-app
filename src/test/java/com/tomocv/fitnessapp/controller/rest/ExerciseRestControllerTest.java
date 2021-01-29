package com.tomocv.fitnessapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomocv.fitnessapp.domain.Exercise;
import com.tomocv.fitnessapp.repository.JpaExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JpaExerciseRepository exerciseRepository;

    @Test
    void getAllExercises() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/exercise")
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @DirtiesContext
    void saveExercise() throws Exception {
        String TEST_MUSCLE_GROUP = "Test Muscle Group";
        String TEST_EXERCISE_NAME = "Test Exercise Name";
        String TEST_DESCRIPTION = "Test Description";
        Integer TEST_NUMBER_OF_REPS = 30;
        Float TEST_WEIGHT = 12.5F;

        Exercise exercise = new Exercise();
        exercise.setMuscleGroup(TEST_MUSCLE_GROUP);
        exercise.setExerciseName(TEST_EXERCISE_NAME);
        exercise.setDescription(TEST_DESCRIPTION);
        exercise.setNumberOfReps(TEST_NUMBER_OF_REPS);
        exercise.setWeight(TEST_WEIGHT);

        this.mockMvc
                .perform(
                        post("/api/exercise")
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exercise))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.muscleGroup").value(TEST_MUSCLE_GROUP))
                .andExpect(jsonPath("$.exerciseName").value(TEST_EXERCISE_NAME))
                .andExpect(jsonPath("$.description").value(TEST_DESCRIPTION));
    }

    @Test
    @DirtiesContext
    void deleteExercise() throws Exception {
        final long TEST_DELETE_EXERCISE_ID = 1L;

        assertTrue(exerciseRepository.findById(TEST_DELETE_EXERCISE_ID).isPresent());
        this.mockMvc
                .perform(
                        delete("/api/exercise/" + TEST_DELETE_EXERCISE_ID)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                )
                .andExpect(status().isNoContent());

        assertTrue(exerciseRepository.findById(TEST_DELETE_EXERCISE_ID).isEmpty());
    }

    @Test
    void deleteExerciseWithoutAdminRights() throws Exception {
        final long TEST_DELETE_EXERCISE_ID = 1L;

        this.mockMvc.perform(
                delete("/api/exercise/" + TEST_DELETE_EXERCISE_ID)
                                .with(csrf())
                                .with(user("test").password("testpassword").roles("USER"))
                )
                .andExpect(status().isForbidden());
    }

    @DirtiesContext
    @Test
    void updateExerciseWeight() throws Exception {
        final long TEST_UPDATE_EXERCISE_ID = 1L;
        final float TEST_UPDATED_WEIGHT = 90F;

        this.mockMvc.perform(
                put("/api/exercise/" + TEST_UPDATE_EXERCISE_ID + "/update-weight?weight=" + TEST_UPDATED_WEIGHT)
                        .with(user("test").password("testpassword").roles("ADMIN", "USER"))
                        .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.weight").value(TEST_UPDATED_WEIGHT));
    }
}
