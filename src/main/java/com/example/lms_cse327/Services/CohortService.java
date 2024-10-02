package com.example.lms_cse327.Services;

import com.example.lms_cse327.Models.Cohort;
import com.example.lms_cse327.Models.Teacher;
import com.example.lms_cse327.Repositories.CohortRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CohortService {

    @Autowired
    private CohortRepo cohortRepo;

    public HashSet<Cohort> showAllCohort(Teacher teacher) {

        return cohortRepo.showAllCohort(teacher);
    }

    public HashSet<Cohort> showAllSubCohort(Teacher teacher, Long id) {

        return cohortRepo.showAllSubCohort(teacher, id);
    }

}
