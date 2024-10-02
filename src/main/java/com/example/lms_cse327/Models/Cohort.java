package com.example.lms_cse327.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cohort_creator_email")
    private Teacher cohortCreator;
    private String cohortName;
    @ManyToMany
    private Set<Student> studentList;
    @ManyToMany
    private Set<Cohort> cohortList;
//    @OneToMany
//    private List<CohortPost> cohortPostList;
  

    public Cohort(Long id, Teacher cohortCreator, String cohortName, Set<Student> studentList, Set<Cohort> cohortList) {
        this.id = id;
        this.cohortCreator = cohortCreator;
        this.cohortName = cohortName;
        this.studentList = studentList;
        this.cohortList = cohortList;
       
    }


    public Cohort(Long id, Teacher cohortCreator, String cohortName) {
        this.id = id;
        this.cohortCreator = cohortCreator;
        this.cohortName = cohortName;
    }

    public Cohort() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getCohortCreator() {
        return cohortCreator;
    }

    public void setCohortCreator(Teacher cohortCreator) {
        this.cohortCreator = cohortCreator;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public Set<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set<Student> studentList) {
        this.studentList = studentList;
    }

    public Set<Cohort> getCohortList() {
        return cohortList;
    }

    public void setCohortList(Set<Cohort> cohortList) {
        this.cohortList = cohortList;
    }

}
