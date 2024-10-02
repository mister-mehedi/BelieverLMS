package com.example.lms_cse327.Models;

import javax.persistence.*;

@Entity
public class Marksheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_email")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    private int obtainedMarks;

    public Quiz getQuiz() {
        return quiz;
    }

    public Student getStudent() {
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Marksheet(Student student, Quiz quiz, int obtainedMarks) {
        this.student = student;
        this.quiz = quiz;
        this.obtainedMarks = obtainedMarks;
    }

    public Marksheet() {
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }
}
