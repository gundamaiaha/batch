package com.example.batchxml.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "LegalEntity")
public class LegalEntityDto {

    @XmlElement(name = "LegalName")
    private String legalName;
}
