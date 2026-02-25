package com.example.quiz_system.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_system.dto.QuizSubmissionDTO;
import com.example.quiz_system.entity.Result;
import com.example.quiz_system.service.QuizService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentController {

    private final QuizService quizService;

    public StudentController(QuizService quizService) {
        this.quizService = quizService;
    }

    // STUDENT: Attempt quiz
    @PostMapping("/quizzes/{id}/submit")
    public Result submitQuiz(@PathVariable Long id,
                             @RequestBody QuizSubmissionDTO submission) {
        return quizService.submitQuiz(id, submission);
    }

    // STUDENT: View own result
    @GetMapping("/results/{quizId}")
    public Result getMyResult(@PathVariable Long quizId,
                              @RequestParam String userName) {
        return quizService.getMyResult(quizId, userName);
    }
}