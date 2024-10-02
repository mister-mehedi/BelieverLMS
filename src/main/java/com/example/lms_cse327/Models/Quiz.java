package com.example.lms_cse327.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String quizName;
    private int numberOfQuestion;
    @ManyToOne
    @JoinColumn(name = "cohort_id")
    private Cohort cohort;
    @OneToMany(cascade=CascadeType.ALL)
    private List<QuizQuestion> quizQuestionList;

    public Quiz(String quizName, int numberOfQuestion, Cohort cohort, List<QuizQuestion> quizQuestionList) {
        this.quizName = quizName;
        this.numberOfQuestion = numberOfQuestion;
        this.cohort = cohort;
        this.quizQuestionList = quizQuestionList;
    }

    public Quiz(String quizName, int numberOfQuestion, Cohort cohort) {
        this.quizName = quizName;
        this.numberOfQuestion = numberOfQuestion;
        this.cohort = cohort;
    }

    public Quiz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public List<QuizQuestion> getQuizQuestionList() {
        return quizQuestionList;
    }

    public void setQuizQuestionList(List<QuizQuestion> quizQuestionList) {
        this.quizQuestionList = quizQuestionList;
    }
}
