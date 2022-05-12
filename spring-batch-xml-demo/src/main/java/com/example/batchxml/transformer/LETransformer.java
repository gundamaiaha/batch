package com.example.batchxml.transformer;

import com.example.batchxml.dto.LERecordDto;
import com.example.batchxml.dto.LegalEntityDto;
import com.example.batchxml.model.LegalEntity;
import com.example.batchxml.model.LegalRecord;
import org.springframework.stereotype.Component;

@Component
public class LETransformer {


    public LegalRecord dtoToEntity(final LERecordDto leRecordDto){
        System.out.println("leRecordDto = " + leRecordDto);
        LegalRecord legalRecord= new LegalRecord();
        legalRecord.setLei(leRecordDto.getLEI());

        LegalEntityDto legalEntityDto = leRecordDto.getLegalEntityDto();
        LegalEntity legalEntity= new LegalEntity();
        legalEntity.setLegalName(legalEntityDto.getLegalName());
        legalEntity.setLegalRecord(legalRecord);

        legalRecord.setLegalEntity(legalEntity);
        return legalRecord;
    }

}
