package com.tomocv.fitnessapp.controller;

import com.tomocv.fitnessapp.domain.Exercise;
import com.tomocv.fitnessapp.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("list")
    public String listExercise(Model model){
        List<Exercise> addedExercises = new ArrayList<>(exerciseService.findAll());
        model.addAttribute("addedExercises", addedExercises);
        return "exercise/exercise-list";
    }

    @GetMapping("new")
    public String addExercise(Model model){
        model.addAttribute("exercise", new Exercise());
        return "exercise/exercise-form";
    }

    @PostMapping("new")
    public String exerciseAdded(@Valid Exercise exercise, Errors errors){
        if(errors.hasErrors()){
            return "exercise/exercise-form";
        }

        exerciseService.save(exercise);

        return "exercise/exercise-added";
    }

    @GetMapping("search")
    public String searchForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "exercise/search";
    }

    @PostMapping("search")
    public String searchResults(Exercise exercise, Model model) {
        Set<Exercise> exerciseSet = exerciseService.findByEnteredQueryData(exercise);
        model.addAttribute("exerciseSet", exerciseSet);
        return "exercise/search";
    }
}
