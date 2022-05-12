package com.example.batchxml.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "LEGAL_ENTITY")
public class LegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String legalName;

    @OneToOne
    @JoinColumn(name = "LEIRecord_IDt")
    private LegalRecord legalRecord;


    @CreationTimestamp
    private Date processDate;
}
