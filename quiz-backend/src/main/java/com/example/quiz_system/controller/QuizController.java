package com.example.quiz_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_system.dto.QuizSubmissionDTO;
import com.example.quiz_system.entity.Quiz;
import com.example.quiz_system.entity.Result;
import com.example.quiz_system.service.QuizService;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping("/{id}/submit")
    public Result submitQuiz(@PathVariable Long id,
                             @RequestBody QuizSubmissionDTO submission) {
        return quizService.submitQuiz(id, submission);
    }
}