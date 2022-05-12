package com.example.batchxml.config;

import com.example.batchxml.dto.LERecordDto;
import com.example.batchxml.model.LegalRecord;
import com.example.batchxml.transformer.LETransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LEProcessor implements ItemProcessor<LERecordDto, LegalRecord> {

    private final LETransformer transformer;


    @Override
    public LegalRecord process(LERecordDto item) throws Exception {
        return transformer.dtoToEntity(item);
    }
}
