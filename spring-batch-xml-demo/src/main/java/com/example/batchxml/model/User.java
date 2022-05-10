package com.example.batchxml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS_INFO")
@XmlRootElement(name = "user")
public class User {

    @Id
    private Integer id;
    private String name;

}
