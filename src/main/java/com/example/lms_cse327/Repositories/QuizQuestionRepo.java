package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizQuestionRepo extends JpaRepository<QuizQuestion, Long> {
}
