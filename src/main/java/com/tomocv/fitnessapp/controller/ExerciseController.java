package com.tomocv.fitnessapp.controller;

import com.tomocv.fitnessapp.domain.Exercise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("exercise")
public class ExerciseController {

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
        return "exercise/exercise-added";
    }
}
