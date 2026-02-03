package com.example.Backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "test_user")
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

}
