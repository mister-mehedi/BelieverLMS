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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CohortController {

    @Autowired
    private CohortRepo cohortRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    private CohortPostRepo cohortPostRepo;
    /**
     * This method creates an empty cohort
     * @param cohortName
     * @return
     */
    @GetMapping("/createCohort")
    public String createCohort(@RequestParam("cohortName") String cohortName) {

        Cohort cohort = new Cohort();

        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());

        cohort.setCohortCreator(teacher);
        cohort.setCohortName(cohortName);

        cohortRepo.save(cohort);

        return "redirect:/navigateGroups";
    }

    /**
     * This method opens selected cohort
     * @param id
     * @param model
     * @param studentList
     * @param cohortList
     * @return
     */
    @RequestMapping("/cohort/{id}")
    public String viewCohort(@PathVariable("id") Long id, Model model,
                             Model studentList, Model cohortList,
                             Model CohortPostList, Model quizListModel) {

        Cohort cohort = cohortRepo.getById(id);
        List<Quiz> quizList = quizRepo.findAllQuizStd(cohort);
        model.addAttribute("cohort", cohort);


        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());

        List<Student> students = studentRepo.findAll();
        Set<Cohort> cohorts = cohortRepo.showAllSubCohort(teacher, id);
        List<CohortPost> cohortPostList = cohortPostRepo.findAllPost(cohort);

        CohortPostList.addAttribute("postList", cohortPostList);
        studentList.addAttribute("students", students);
        cohortList.addAttribute("cohorts",cohorts);
        quizListModel.addAttribute("quizList", quizList);

        return "inside_cohort_teacher";

    }


    /**
     * This method adds students in cohort
     * @param addToCohortID
     * @param studentID
     * @return
     */
    @GetMapping("/addStudent/{cohortID}/{studentID}")
    public String addStudentMember(@PathVariable("cohortID") Long addToCohortID, @PathVariable("studentID") String studentID) {

        Student student = studentRepo.findByStudentID(studentID);
        Cohort cohort = cohortRepo.findById(addToCohortID).get();
        Set<Student> studentArrayList = cohort.getStudentList();
        if(studentArrayList == null){
            studentArrayList = new HashSet<>();
        }else {
            studentArrayList.add(student);
        }

        if (cohortRepo.findById(addToCohortID).isPresent()) {
            Cohort cohort1 = cohortRepo.findById(addToCohortID).get();
            cohort1.setStudentList(studentArrayList);
            cohortRepo.save(cohort);
        }

        return "redirect:/cohort/"+addToCohortID;
    }

    /**
     * This method adds subCohort in cohort
     * @param addToCohortID
     * @param subCohortID
     * @return
     */
    @GetMapping("/addSubCohort/{addToCohortID}/{subCohortID}")
    public String addCohortMember(@PathVariable("addToCohortID") Long addToCohortID, @PathVariable("subCohortID") Long subCohortID) {

        Cohort subCohort = cohortRepo.getById(subCohortID);
        Cohort cohort = cohortRepo.findById(addToCohortID).get();
        Set<Cohort> cohortList = cohort.getCohortList();
        if(cohortList == null){
            cohortList = new HashSet<>();
        }else {
            cohortList.add(subCohort);
        }

        if (cohortRepo.findById(addToCohortID).isPresent()) {
            Cohort cohort1 = cohortRepo.findById(addToCohortID).get();
            cohort1.setCohortList(cohortList);
            cohortRepo.save(cohort);
        }

        return "redirect:/cohort/"+addToCohortID;
    }

    /**
     * This method returns current logged-in user
     * @return
     */
    public DefaultOidcUser currentLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (DefaultOidcUser) auth.getPrincipal();
    }

}
