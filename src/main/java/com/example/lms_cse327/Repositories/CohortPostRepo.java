package com.example.lms_cse327.Repositories;

import com.example.lms_cse327.Models.Cohort;
import com.example.lms_cse327.Models.CohortPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CohortPostRepo extends JpaRepository<CohortPost, Long> {

    @Query("from CohortPost cp where cp.postOfCohort=?1")
    List<CohortPost> findAllPost(Cohort cohort);

}
