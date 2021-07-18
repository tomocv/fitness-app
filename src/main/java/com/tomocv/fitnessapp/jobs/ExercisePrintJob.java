package com.tomocv.fitnessapp.jobs;

import com.tomocv.fitnessapp.domain.Exercise;
import com.tomocv.fitnessapp.repository.JpaExerciseRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ExercisePrintJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(ExercisePrintJob.class);

    private final JpaExerciseRepository jpaExerciseRepository;

    public ExercisePrintJob(JpaExerciseRepository jpaExerciseRepository) {
        this.jpaExerciseRepository = jpaExerciseRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Iterable<Exercise> exercises = jpaExerciseRepository.findAll();

        if(exercises.iterator().hasNext()) {
            log.info("These are the exercises currently entered in the app");
            exercises.forEach(
                    it -> log.info(it.getExerciseName())
            );
        } else {
            log.info("There are no exercises in the app");
        }
    }
}
