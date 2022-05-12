package com.example.batchxml.config;

import com.example.batchxml.dto.LERecordDto;
import com.example.batchxml.dto.LegalEntityDto;
import com.example.batchxml.model.LegalEntity;
import com.example.batchxml.model.LegalRecord;
import com.example.batchxml.model.User;
import com.example.batchxml.repository.LERecordRepository;
import com.example.batchxml.repository.LERepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.security.PublicKey;

@Configuration
public class LEBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    private LERecordRepository leRecordRepository;

    @Autowired
    private LEProcessor leProcessor;



    @Bean
    public StaxEventItemReader<LERecordDto> reader(){
        Jaxb2Marshaller marshaller= new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(LERecordDto.class);
        marshaller.setSupportDtd(true);

        return new StaxEventItemReaderBuilder<LERecordDto>()
                .name("LEReader")
                .resource(new FileSystemResource("src/main/resources/le_data.xml"))
                .addFragmentRootElements("LEIRecord")
                .unmarshaller(marshaller)

                .build();
    }

    @Bean
    public RepositoryItemWriter<LegalRecord> writer(){
        RepositoryItemWriter<LegalRecord> writer =  new RepositoryItemWriter<>();
        writer.setRepository(leRecordRepository);
        writer.setMethodName("save");
        return writer;
    }
//    @Bean
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
//        return asyncTaskExecutor;
//    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("importLEStep")
                .<LERecordDto,LegalRecord>chunk(10)

                .reader(reader())
                .processor(leProcessor)
                .writer(writer())
                //.taskExecutor(taskExecutor)
                //.taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("importLEJob")
                .flow(step1())
                .end().build();
    }



//
//    @Bean
//    public JobLauncher jobLauncher() throws Exception {
//        SimpleJobLauncher jobLauncher= new SimpleJobLauncher();
//        jobLauncher.setTaskExecutor(taskExecutor());
//        jobLauncher.afterPropertiesSet();
//        return jobLauncher;
//    }
//
//
//    @Bean
//    public TaskExecutor taskExecutor(){
//        SimpleAsyncTaskExecutor asyncTaskExecutor= new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(10);
//         return asyncTaskExecutor;
//    }



}
