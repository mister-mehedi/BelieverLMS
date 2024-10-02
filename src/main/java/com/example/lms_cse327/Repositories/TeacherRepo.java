package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.Student;
import com.example.lms_cse327.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepo extends JpaRepository<Teacher, String> {

    @Query("from Teacher t where t.email=?1")
    Teacher findByEmail(String email);
}
