package com.example.lms_cse327.Models;

import javax.persistence.*;

@Entity
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String question;
    private String answerGivenByTeacher;
    private String answerType;
    private String answerGivenByStudent;
    private String optA;
    private String optB;
    private String optC;


    public QuizQuestion(String question, String answerGivenByTeacher, String answerType, String answerGivenByStudent) {
        this.question = question;
        this.answerGivenByTeacher = answerGivenByTeacher;
        this.answerType = answerType;
        this.answerGivenByStudent = answerGivenByStudent;
    }



    public QuizQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerGivenByTeacher() {
        return answerGivenByTeacher;
    }

    public void setAnswerGivenByTeacher(String answerGiven) {
        this.answerGivenByTeacher = answerGiven;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getAnswerGivenByStudent() {
        return answerGivenByStudent;
    }

    public void setAnswerGivenByStudent(String answerGivenByStudent) {
        this.answerGivenByStudent = answerGivenByStudent;
    }
}
