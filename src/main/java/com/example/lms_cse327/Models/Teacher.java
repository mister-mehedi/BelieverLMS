package com.example.lms_cse327.Models;

import javax.persistence.*;

@Entity
public class Teacher extends User{

    private String initial;
    private boolean enable;
    private String role;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String facImage;

    public Teacher(String email, String fullName, String initial, boolean enable, String role, String facImage) {
        super(email, fullName);
        this.initial = initial;
        this.enable = enable;
        this.role = role;
        this.facImage = facImage;
    }


    public Teacher() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getFacImage() {
        return facImage;
    }

    public void setFacImage(String facImage) {
        this.facImage = facImage;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
