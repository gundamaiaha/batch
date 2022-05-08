package com.example.batchxml.config;

import com.example.batchxml.model.User;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public StaxEventItemReader<User> reader(){
        StaxEventItemReader<User> reader= new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource("input.xml"));
        reader.setFragmentRootElementName("user");

        Map<String,String> aliases= new HashMap<>();
        aliases.put("user", "com.example.batchxml.model.User");

        //XStream

        return reader;
    }


}
