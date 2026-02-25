package com.example.quiz_system.service;

import java.util.List;

import com.example.quiz_system.dto.QuizSubmissionDTO;
import com.example.quiz_system.entity.Quiz;
import com.example.quiz_system.entity.Result;

public interface QuizService {

    Quiz createQuiz(Quiz quiz);

    List<Quiz> getAllQuizzes();

    Quiz getQuizById(Long id);

    Result submitQuiz(Long quizId, QuizSubmissionDTO submission);
    Result getMyResult(Long quizId, String userName);
}