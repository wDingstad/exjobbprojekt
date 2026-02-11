package com.example.Backend.dto;

public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;



    public UserDTO(Integer id, String first_name, String last_name, String email, String phone_number) {

        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.phoneNumber = phone_number;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
