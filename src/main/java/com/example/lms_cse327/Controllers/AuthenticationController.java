package com.example.lms_cse327.Controllers;

import com.example.lms_cse327.Models.Student;
import com.example.lms_cse327.Models.Teacher;
import com.example.lms_cse327.Repositories.TeacherRepo;
import com.example.lms_cse327.Repositories.StudentRepo;
import com.example.lms_cse327.Services.CohortService;
import com.example.lms_cse327.Utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class AuthenticationController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private CohortService cohortService;
    
    @GetMapping("/navigateMaterials")
    public String navigateMaterials(){
        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());
        if (student!=null){
            return "materialsStudentCohort";
        }else {
            return "materialsTeacherCohort";
        }
    }
    
    @GetMapping("/navigatePeople")
    public String navigatePeople(){
        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());
        if (student!=null){
            return "peopleStudentCohort";
        }else {
            return "peopleTeacherCohort";
        }
    }
    
    @GetMapping("/navigateGroups")
    public String navigateGroups(Model userModel, Model modelCohort){
        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());
        if (student!=null){
            return "redirect:/studentCohort";
        }else {
            userModel.addAttribute("user", teacher);
            modelCohort.addAttribute("cohortList", cohortService.showAllCohort(teacher));
            return "outsideTeacherSub-cohort";
        }
    }
    
    @GetMapping("/navigateGroupView")
    public String navigateGroupView(){
        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());
        if (student!=null){
            return "displayStudentSub-cohort";
        }else {
            return "displayTeacherSub-cohort";
        }
    }


    /**
     * This method is created To navigate which cohort to show student or teacher
     *
     * @param userModel   it returns user object
     * @param modelCohort it returns cohort list
     * @return returns studentcohort or teachercohort html page
     */
    // new added -->>>>>>>>>>>>>>
    @GetMapping("/navigateCohort")
    public String navigateCohort(Model userModel, Model modelCohort) {


        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());

        if (student != null) {


            return "redirect:/studentCohort";
            //return "studentcohort";
            // return "outsidestudentcohort";
        } else {
            userModel.addAttribute("user", teacher);
            modelCohort.addAttribute("cohortList", cohortService.showAllCohort(teacher));
            return "teachercohort";
            //   return "outsideteachercohort";
        }

    }

    /**
     * This method shows the dashboard/ role selector page. "/home" is the default success url after logging in
     *
     * @param modelCohort
     * @param userModel
     * @return
     */

    @GetMapping("/home")
    public String showDashboard(Model modelCohort, Model userModel) {

        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        Teacher teacher = teacherRepo.findByEmail(currentLoggedInUser().getEmail());

        if (student == null && teacher == null) {
            return "role";
        } else {
            if (student != null) {
                if (student.isEnable()) {
                    userModel.addAttribute("user", student);
                    return "studentdashboard";
                } else {
                    return "role";
                }
            } else {
                if (teacher.isEnable()) {
                    userModel.addAttribute("user", teacher);
                    modelCohort.addAttribute("cohortList", cohortService.showAllCohort(teacher));

                    return "teacherdashboard";
                } else {
                    return "role";
                }
            }
        }

    }

    /**
     * This method shows the student data taking form
     *
     * @return view
     */
    @GetMapping("/student")
    public String selectStudent() {
        return "student";
    }

    /**
     * This method shows the teacher data taking form
     *
     * @return view
     */
    @GetMapping("/teacher")
    public String selectTeacher() {
        return "teacher";
    }

    /**
     * This method saves the student data and redirect to dashboard
     *
     * @param student
     * @param multipartFile
     * @param model
     * @return
     * @throws IOException
     */
    // changed RequestMapping to PostMapping -->>>>>>>>>>>>>>
    @PostMapping("/studentData")
    public String takeStudentData(Student student, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {


        if (multipartFile == null) {
            student.setStdImage(null);
        } else {
            if (fileUploadUtil.saveFile(multipartFile)) {
                System.out.println("Saved Imaged successfully");
            }
            student.setStdImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }

        student.setEmail(currentLoggedInUser().getEmail());
        student.setFullName(currentLoggedInUser().getFullName());
        student.setEnable(true);
        student.setRole("Student");


        studentRepo.save(student);

        // comment next 3 coding lines
        Student student1 = studentRepo.findByEmail(currentLoggedInUser().getEmail());
        model.addAttribute("user", student1);

        return "studentdashboard";

        // new added -->>>>>>>>>>>>>>
//        return "redirect:/stddash";
    }

    /**
     * This method saves the teacher data and redirect to teacher dashboard
     *
     * @param teacher
     * @param multipartFile
     * @param model
     * @return
     * @throws IOException
     */
    // changed RequestMapping to PostMapping -->>>>>>>>>>>>>>
    @PostMapping("/teacherData")
    public String takeTeacherData(Teacher teacher, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {

        if (multipartFile == null) {
            teacher.setFacImage(null);
        } else {
            if (fileUploadUtil.saveFile(multipartFile)) {
                System.out.println("Saved Imaged successfully");
            }
            teacher.setFacImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }

        teacher.setEmail(currentLoggedInUser().getEmail());
        teacher.setFullName(currentLoggedInUser().getFullName());
        teacher.setEnable(true);
        teacher.setRole("Teacher");

        teacherRepo.save(teacher);

        // comment next 3 coding lines
        Teacher teacher1 = teacherRepo.findByEmail(currentLoggedInUser().getEmail());
        model.addAttribute("user", teacher1);
//
//        return "TeacherHome";

        // new added -->>>>>>>>>>>>>>
        return "teacherdashboard";

    }


    /**
     * This method returns the current logged-in user
     *
     * @return
     */

    public DefaultOidcUser currentLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (DefaultOidcUser) auth.getPrincipal();
    }


}
