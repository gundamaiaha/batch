package com.example.batchxml.dto;

import com.example.batchxml.model.LegalEntity;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LEIRecord")
public class LERecordDto {

    @XmlElement(name = "LEI")
    private String LEI;

    @XmlElement(name = "LegalEntity")
    private LegalEntityDto legalEntityDto;
}
