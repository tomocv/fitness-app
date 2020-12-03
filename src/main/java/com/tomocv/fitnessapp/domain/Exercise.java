package com.tomocv.fitnessapp.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Exercise {

    private Long id;

    @NotEmpty(message = "You haven''t entered a muscle group")
    @Size(min = 2, max = 20, message = "The muscle group should be between 2 and 20 characters long")
    private String muscleGroup;

    @NotEmpty(message = "You haven''t entered an exercise name")
    @Size(min = 2, max = 30, message = "The exercise name should be between 2 and 30 characters long")
    private String exerciseName;

    @Min(value=1, message = "The number of repetitions must be above 0")
    private Integer numberOfReps;

    @Min(value=0, message = "The weight cannot be a negative number")
    private Float weight;

    @NotEmpty(message = "You haven''t entered a description")
    @Size(min = 5, max = 120, message = "The description should be between 5 and 120 characters long")
    private String description;

    public Exercise(Long id, String muscleGroup, String exerciseName, Integer numberOfReps, Float weight, String description) {
        this.id = id;
        this.muscleGroup = muscleGroup;
        this.exerciseName = exerciseName;
        this.numberOfReps = numberOfReps;
        this.weight = weight;
        this.description = description;
    }

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Integer getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(Integer numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
