package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface    StudentRepo extends JpaRepository<Student, String> {

    @Query("from Student s where s.email=?1")
    Student findByEmail(String email);

    @Query("from Student s where s.stdId=?1")
    Student findByStudentID(String studentID);

}
