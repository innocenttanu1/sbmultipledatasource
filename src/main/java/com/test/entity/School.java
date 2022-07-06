package com.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "school", schema = "schooldb")
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    // Generate Getters and Setters...
}