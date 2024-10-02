package com.example.lms_cse327.Controllers;

import com.example.lms_cse327.Models.*;
import com.example.lms_cse327.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentViewController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    CohortRepo cohortRepo;
    @Autowired
    CohortPostRepo cohortPostRepo;
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    MarkSheetRepo markSheetRepo;


    @GetMapping("/studentCohort")
    public String viewAllCohortStudent(Model model, Model userModel) {

        List<Long> cohortIdList = cohortRepo.showStudentCohort(currentLoggedInUser().getEmail());
        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        userModel.addAttribute("user", student);

        List<Cohort> cohortList = new ArrayList<>();

        for (Long aLong : cohortIdList) {
            if (cohortRepo.findById(aLong).isPresent()) {
                Cohort cohort = cohortRepo.findById(aLong).get();
                cohortList.add(cohort);
            }
        }

        model.addAttribute("stdCohortList", cohortList);

        return "outsideStudentSub-cohort";
    }

    @GetMapping("/viewStdCohort/{id}")
    public String viewStdCohort(@PathVariable("id") Long id, Model model,
                                Model studentList, Model quizListModel,
                                Model CohortPostList) {
        Cohort cohort = cohortRepo.getById(id);

        model.addAttribute("cohort", cohort);

        List<Quiz> quizList = quizRepo.findAllQuizStd(cohort);

        List<Student> students = studentRepo.findAll();


        List<CohortPost> cohortPostList = cohortPostRepo.findAllPost(cohort);

        CohortPostList.addAttribute("postList", cohortPostList);
        studentList.addAttribute("students", students);
        quizListModel.addAttribute("quizList", quizList);

        return "inside_cohort_student";

    }

    @GetMapping("/viewMarkSheetStd")
    public String viewMarkSheetStd(Model model, Model user) {

        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        List<Marksheet> marksheet = markSheetRepo.marksheetOfStd(student);
        model.addAttribute("markSheet", marksheet);
        user.addAttribute("user", student);

        return "stdMarksheet";
    }

//    @GetMapping("/viewAllStudentMarks")
//    public String viewAllStudentReport(Model model) {
//
//
//
//
//    }

    public DefaultOidcUser currentLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (DefaultOidcUser) auth.getPrincipal();
    }

}
