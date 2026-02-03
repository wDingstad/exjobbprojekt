package com.example.Backend.models;
import jakarta.persistence.*;



@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;

    //get all info about a user
    //public getUser(int id){}

    public int getId() {
        return id;
    }
    public String getFirst_name(){
        return first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public String getEmail(){
        return email;
    }
    public String getPhone_number(){
        return phone_number;
    }


}
