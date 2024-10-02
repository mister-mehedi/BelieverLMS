package com.example.lms_cse327.Models;

import javax.persistence.*;

@Entity
public class Student extends User{


    private String stdId;
    private boolean enable;
    private String role;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String stdImage;

    public Student(String email, String fullName, String stdId, boolean enable, String role, String stdImage) {
        super(email, fullName);
        this.stdId = stdId;
        this.enable = enable;
        this.role = role;
        this.stdImage = stdImage;
    }

    public Student() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdImage() {
        return stdImage;
    }

    public void setStdImage(String stdImage) {
        this.stdImage = stdImage;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
