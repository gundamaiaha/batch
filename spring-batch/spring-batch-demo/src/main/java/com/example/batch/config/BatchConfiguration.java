package com.example.batch.config;

import com.example.batch.model.User;
import com.example.batch.repository.ProfileRepository;
import com.example.batch.repository.UserRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private ProfileRepository profileRepository;

    @Bean
    public RepositoryItemReader<User> reader(){
        RepositoryItemReader<User> reader =  new RepositoryItemReader<>();
        reader.setRepository(userRepository);
        reader.setMethodName("findByStatusAndEmailVerified");

        List<Object> queryMethodArguments =  new ArrayList<>();
        queryMethodArguments.add("APPROVED");
        queryMethodArguments.add(Boolean.TRUE);

        reader.setArguments(queryMethodArguments);
        reader.setPageSize(100);


    }


}
