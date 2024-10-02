package com.example.lms_cse327.Models;

import javax.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    private String email;
    private String fullName;


    public User(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public User() {
        super();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
