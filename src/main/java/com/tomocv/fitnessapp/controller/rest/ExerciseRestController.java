package com.tomocv.fitnessapp.controller.rest;

import com.tomocv.fitnessapp.domain.Exercise;
import com.tomocv.fitnessapp.repository.JpaExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("api/exercise")
public class ExerciseRestController {

    private final JpaExerciseRepository jpaExerciseRepository;

    public ExerciseRestController(JpaExerciseRepository jpaExerciseRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
    }

    @GetMapping
    public Iterable<Exercise> findAll() {
        return jpaExerciseRepository.findAll();
    }

    @GetMapping("{id}")
    public Exercise findOne(@PathVariable Long id) {
        return jpaExerciseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise was not found")
                );
    }
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Exercise save(@Valid @RequestBody Exercise exercise) {
        if(exercise.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exercise ID must be left empty when creating an exercise");
        }

        if(jpaExerciseRepository.existsByExerciseName(exercise.getExerciseName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Exercise with the same name already exist");
        }

        return jpaExerciseRepository.save(exercise);
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if(!jpaExerciseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Exercise with the provided ID does not exist!");
        }

        jpaExerciseRepository.deleteById(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}/update-weight")
    public Exercise updateWeight(@PathVariable Long id, @RequestParam @Min(value=0, message = "The weight cannot be a negative number") Float weight) {
        final Exercise exercise = jpaExerciseRepository.findById(id)
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.CONFLICT, "Exercise with the provided ID does not exist!")
                );

        exercise.setWeight(weight);

        return jpaExerciseRepository.save(exercise);
    }
}
