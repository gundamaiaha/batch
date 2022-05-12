package com.example.batchxml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobLauncher jobLauncher;

//    @Qualifier("usersJob")
    private final Job job;

    private final TaskExecutor taskExecutor;

//    @Qualifier("leJob")
//    private final Job leJob;

//    @GetMapping("/usersJob")
//    public String runUsersJob(){
//        JobParameters jobParameters= new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis())
//                .toJobParameters();
//        try {
//            jobLauncher.run(usersJob, jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
//            e.printStackTrace();
//        }
//        return "Users Job finished successgully";
//    }

    @GetMapping("/leJob")
    public String runLeJob(){
        JobParameters jobParameters= new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        try {
           // simpleJobLauncher.setTaskExecutor(taskExecutor);
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
        return "LE Job finished successgully";
    }

}
