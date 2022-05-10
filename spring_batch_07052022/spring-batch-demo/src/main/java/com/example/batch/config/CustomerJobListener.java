package com.example.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

import java.util.ArrayList;

@Slf4j
public class CustomerJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before Job :::");
        log.info(jobExecution.getStatus().getBatchStatus().getDeclaringClass().getName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After Job :::");
        log.info(jobExecution.getStatus().getBatchStatus().getDeclaringClass().getName());

//        int commitCount = new ArrayList<StepExecution>(jobExecution.getStepExecutions())
//                .get(0).getCommitCount();
     //   log.info("No of Records committed :: "+commitCount);
      //  int stepExecutionsSize = jobExecution.getStepExecutions().size();

        new ArrayList<StepExecution>(jobExecution.getStepExecutions()).stream()
                        .map(StepExecution::getSummary).forEach(System.out::println);

     //   System.out.println("stepExecutionsSize = " + stepExecutionsSize);
    }
}
