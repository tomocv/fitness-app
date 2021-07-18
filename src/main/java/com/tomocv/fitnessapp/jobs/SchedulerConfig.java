package com.tomocv.fitnessapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail exercisePrintJobDetail() {
        return JobBuilder
                .newJob(ExercisePrintJob.class)
                .withIdentity("exercisePrintJob")
                .storeDurably()
                .build();
    }

    @Bean
    public SimpleTrigger exercisePrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(exercisePrintJobDetail())
                .withIdentity("exercisePrintTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
