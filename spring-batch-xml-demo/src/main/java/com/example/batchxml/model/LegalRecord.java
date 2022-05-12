package com.example.batchxml.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "LERecord")
public class LegalRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lei;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "legalRecord")
    private LegalEntity legalEntity;


    @CreationTimestamp
    private Date createdDate;

}
