package com.example.batchxml.config;

import com.example.batchxml.model.User;
import com.example.batchxml.repository.UserRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

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
   private UserRepository userRepository;





    @Bean
    public StaxEventItemReader<User> reader(){
        Jaxb2Marshaller marshaller= new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(User.class);
        marshaller.setSupportDtd(true);

        return new StaxEventItemReaderBuilder<User>()
                .name("UserReader")
                .resource(new FileSystemResource("src/main/resources/input.xml"))
                .addFragmentRootElements("user")
                .unmarshaller(marshaller)
                .build();
    }

    @Bean
    public RepositoryItemWriter<User> writer(){
        RepositoryItemWriter<User> writer =  new RepositoryItemWriter<>();
        writer.setRepository(userRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("importUsersStep")
                .<User,User>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Job importUsersJob(){
        return jobBuilderFactory.get("importUsersJob")
                .flow(step1())
                .end().build();
    }


}
