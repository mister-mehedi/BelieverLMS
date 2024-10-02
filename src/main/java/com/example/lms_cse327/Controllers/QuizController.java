package com.example.lms_cse327.Controllers;

import com.example.lms_cse327.Models.*;
import com.example.lms_cse327.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    CohortRepo cohortRepo;

    @Autowired
    QuizQuestionRepo quizQuestionRepo;

    @Autowired
    MarkSheetRepo markSheetRepo;

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/navigateQuiz/{QuizID}")
    public String viewQuiz(Model model, @PathVariable("QuizID") Long quizID){

        Quiz quiz = quizRepo.getById(quizID);

        model.addAttribute("quiz",quiz);

        return "QuizQuestionAdd";
    }

    @GetMapping("/createQuiz/{cohortID}")
    public String createQuiz(@RequestParam("quizName") String quizName,
                             @RequestParam("quizQuestionNum") int quizQuestionNum,
                             @PathVariable("cohortID")  Long cohortID, Model model) {

        Quiz quiz = new Quiz();
        Cohort cohort = cohortRepo.getById(cohortID);


        quiz.setQuizName(quizName);
        quiz.setCohort(cohort);
        quiz.setNumberOfQuestion(quizQuestionNum);

        quizRepo.save(quiz);
        model.addAttribute("quiz", quiz);


        return "QuizQuestionAdd";
    }




    @RequestMapping("/addQuestion/{quizID}/{questionType}")
    public String addQuestionToQuiz(@PathVariable("quizID") Long quizID,
                                    @PathVariable("questionType") String questionType,
                                    @RequestParam("question") String quizQuestion,
                                    @RequestParam("optA") Optional<String> optA,
                                    @RequestParam("optB") Optional<String> optB,
                                    @RequestParam("optC") Optional<String> optC,
                                    @RequestParam("Canswer") String cAnswer) {
        //Need to make an algorithm for getting answer by scanning the pdf then set the answer.

        QuizQuestion question = new QuizQuestion();


        Quiz quiz = quizRepo.getById(quizID);
        List<QuizQuestion> quizQuestionList = quiz.getQuizQuestionList();
        if(quizQuestionList == null){
            quizQuestionList = new ArrayList<>();
        }else {

            if (questionType.equals("mcq") && optA.isPresent() && optB.isPresent() && optC.isPresent()){
                question.setOptA(optA.get());
                question.setOptB(optB.get());
                question.setOptC(optC.get());
            }

            question.setAnswerGivenByTeacher(cAnswer);
            question.setQuestion(quizQuestion);
            question.setAnswerType(questionType);
            quizQuestionList.add(question);
        }

        if (quizRepo.findById(quizID).isPresent()) {
            Quiz quiz1 = quizRepo.findById(quizID).get();
            quiz1.setQuizQuestionList(quizQuestionList);
            quizRepo.save(quiz);
        }

        return "redirect:/navigateQuiz/"+quiz.getId();
    }


    //teacherView --> creating quiz
    @RequestMapping("/quiz/{id}")
    public String viewQuizTeacher(@PathVariable("id") Long id,
                             Model model) {

        Quiz quiz = quizRepo.getById(id);
        model.addAttribute("quiz", quiz);
//        List<QuizQuestion> quizQuestions = quizRepo.findQuizQuestionList();
//        quizQuestionList.addAttribute("quizQuestions", quizQuestions);

        return "quizCreator";
    }

    private int questionCount = 0;
    @GetMapping("quizStudent/{id}")
    public String viewQuizStudent(@PathVariable("id") Long id,Model model, Model quizL){
        Quiz quiz = quizRepo.getById(id);
        List<QuizQuestion> quizQuestions = quiz.getQuizQuestionList();
        quizL.addAttribute("quiz", quiz);
        if (questionCount!=quiz.getNumberOfQuestion()) {
            model.addAttribute("quizQ", quizQuestions.get(questionCount));
        }
        return "QuizStudentView";
    }



    int correct = 0;

    @RequestMapping("/processQuizStudent/{id}")
    public String processQuizStudent(@PathVariable("id") Long id,Model quizQuestionList,
                                  @RequestParam("answerByStd") String answerByStd,
                                     Model result){

        //need to add algorithm for processing student's answer validation

        Quiz quiz = quizRepo.getById(id);

        List<QuizQuestion> quizQuestions = quizRepo.findQuizQuestionList(quiz.getId());

//        for (int i = 0; i < quiz.getNumberOfQuestion(); i++) {
//            quizQuestions.get(i).setAnswerGivenByStudent(answerByStd);
//            quizQuestionRepo.save(quizQuestions.get(i));
//        }

        quizQuestions.get(questionCount).setAnswerGivenByStudent(answerByStd);
        quizQuestionRepo.save(quizQuestions.get(questionCount));



        if (quizQuestions.get(questionCount).getAnswerGivenByTeacher().equals(quizQuestions.get(questionCount).getAnswerGivenByStudent())){
            correct++;
        }



        questionCount++;
        quizQuestionList.addAttribute("quizQuestions", quizQuestions);

        System.out.println("question count : " + questionCount);
        System.out.println("correct : " + correct);



        if (questionCount != quiz.getNumberOfQuestion()){
            return "redirect:/quizStudent/"+id;
        }



        result.addAttribute("marks", correct);

        Marksheet marksheet = new Marksheet();
        Student student = studentRepo.findByEmail(currentLoggedInUser().getEmail());

        marksheet.setQuiz(quiz);
        marksheet.setStudent(student);
        marksheet.setObtainedMarks(correct);
        markSheetRepo.save(marksheet);

        return "quizResult";
    }


    public DefaultOidcUser currentLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (DefaultOidcUser) auth.getPrincipal();
    }




}
