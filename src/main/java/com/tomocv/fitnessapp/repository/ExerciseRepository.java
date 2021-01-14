package com.tomocv.fitnessapp.repository;

import com.tomocv.fitnessapp.domain.Exercise;

import java.util.Optional;
import java.util.Set;

public interface ExerciseRepository {

    Set<Exercise> findAll();

    Optional<Exercise> save(Exercise exercise);
}
