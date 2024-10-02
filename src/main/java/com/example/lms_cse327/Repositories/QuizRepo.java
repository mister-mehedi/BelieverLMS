package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.Cohort;
import com.example.lms_cse327.Models.Quiz;
import com.example.lms_cse327.Models.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz, Long> {

    @Query("select q.quizQuestionList from Quiz q where q.id=?1")
    List<QuizQuestion> findQuizQuestionList(Long id);

    @Query("from Quiz q where q.cohort=?1")
    List<Quiz> findAllQuizStd(Cohort cohort);
}
