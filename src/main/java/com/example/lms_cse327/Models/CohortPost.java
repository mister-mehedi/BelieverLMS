package com.example.lms_cse327.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CohortPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_of_cohort_id")
    private Cohort postOfCohort;
    private String postDesc;
    @OneToMany(cascade=CascadeType.ALL)
    private List<FileInfo> listOfAttachedFiles;


    public Cohort getPostOfCohort() {
        return postOfCohort;
    }

    public void setPostOfCohort(Cohort postOfCohort) {
        this.postOfCohort = postOfCohort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public List<FileInfo> getListOfAttachedFiles() {
        return listOfAttachedFiles;
    }

    public void setListOfAttachedFiles(List<FileInfo> listOfAttachedFiles) {
        this.listOfAttachedFiles = listOfAttachedFiles;
    }
}
