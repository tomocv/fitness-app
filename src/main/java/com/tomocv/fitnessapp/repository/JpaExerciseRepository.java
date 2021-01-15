package com.tomocv.fitnessapp.repository;

import com.tomocv.fitnessapp.domain.Exercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface JpaExerciseRepository extends CrudRepository<Exercise, Long> {

    List<Exercise> findAll();

    Set<Exercise> findAllByMuscleGroupContainingIgnoreCase(String muscleGroup);

    Set<Exercise> findAllByExerciseNameContainingIgnoreCase(String exerciseName);

    Set<Exercise> findAllByMuscleGroupContainingIgnoreCaseAndExerciseNameContainingIgnoreCase(String muscleGroup, String exerciseName);

    boolean existsByExerciseName(String exerciseName);
}
