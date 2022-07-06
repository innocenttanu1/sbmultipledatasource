package com.test.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student", schema = "studentdb")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    // Generate Getters and Setters...

}
