package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.Cohort;
import com.example.lms_cse327.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public interface CohortRepo extends JpaRepository<Cohort, Long> {

    @Query("from Cohort c where c.cohortCreator=?1")
    HashSet<Cohort> showAllCohort(Teacher teacher);

    @Query("from Cohort c where c.cohortCreator=?1  and c.id<>?2")
    HashSet<Cohort> showAllSubCohort(Teacher teacher, Long id);

    @Query(value = "SELECT cohort_student_list.cohort_id from cohort_student_list WHERE cohort_student_list.student_list_email=?1", nativeQuery = true)
    List<Long> showStudentCohort(String email);


//SELECT cohort_student_list.cohort_id from cohort_student_list WHERE cohort_student_list.student_list_email='ahmed.nehal@northsouth.edu' ;





}
