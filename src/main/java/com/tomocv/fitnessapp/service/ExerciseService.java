package com.tomocv.fitnessapp.service;

import com.tomocv.fitnessapp.domain.Exercise;
import com.tomocv.fitnessapp.repository.ExerciseRepository;
import com.tomocv.fitnessapp.repository.JpaExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final JpaExerciseRepository jpaExerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, JpaExerciseRepository jpaExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.jpaExerciseRepository = jpaExerciseRepository;
    }

    public Optional<Exercise> save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public List<Exercise> findAll() {
        return jpaExerciseRepository.findAll();
    }

    public Set<Exercise> findByEnteredQueryData(Exercise exercise) {
        boolean isMuscleGroupFilled = exercise.getMuscleGroup() != null && !exercise.getMuscleGroup().isBlank();
        boolean isExerciseNameFilled = exercise.getExerciseName() != null && !exercise.getExerciseName().isBlank();

        if(isMuscleGroupFilled && isExerciseNameFilled) {
            return jpaExerciseRepository.findAllByMuscleGroupContainingIgnoreCaseAndExerciseNameContainingIgnoreCase(exercise.getMuscleGroup(), exercise.getExerciseName());
        }

        if(isMuscleGroupFilled) {
            return jpaExerciseRepository.findAllByMuscleGroupContainingIgnoreCase(exercise.getMuscleGroup());
        }

        if(isExerciseNameFilled){
            return jpaExerciseRepository.findAllByExerciseNameContainingIgnoreCase(exercise.getExerciseName());
        }

        return Collections.emptySet();
    }
}
