package com.tomocv.fitnessapp.controller;

import com.tomocv.fitnessapp.domain.Exercise;
import com.tomocv.fitnessapp.repository.ExerciseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("exercise")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("list")
    public String listExercise(Model model){
        List<Exercise> addedExercises = new ArrayList<>(exerciseRepository.findAll());
        model.addAttribute("addedExercises", addedExercises);
        return "exercise/exercise-list";
    }

    @GetMapping("new")
    public String addExercise(Model model){
        model.addAttribute("exercise", new Exercise());
        return "exercise/exercise-form";
    }

    @PostMapping("added")
    public String exerciseAdded(@Valid Exercise exercise, Errors errors){
        if(errors.hasErrors()){
            return "exercise/exercise-form";
        }

        exerciseRepository.save(exercise);

        return "exercise/exercise-added";
    }
}
