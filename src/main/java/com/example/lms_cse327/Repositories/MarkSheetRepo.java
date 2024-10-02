package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.Marksheet;
import com.example.lms_cse327.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkSheetRepo extends JpaRepository<Marksheet, Long> {

    @Query("from Marksheet m where m.student=?1")
    List<Marksheet> marksheetOfStd(Student student);
}
