package com.example.quiz_system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quiz_system.dto.QuizSubmissionDTO;
import com.example.quiz_system.entity.Question;
import com.example.quiz_system.entity.Quiz;
import com.example.quiz_system.entity.Result;
import com.example.quiz_system.exception.InvalidSubmissionException;
import com.example.quiz_system.exception.QuizNotFoundException;
import com.example.quiz_system.repository.QuizRepository;
import com.example.quiz_system.repository.ResultRepository;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ResultRepository resultRepository;

    public QuizServiceImpl(QuizRepository quizRepository, ResultRepository resultRepository) {
        this.quizRepository = quizRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        quiz.getQuestions().forEach(q -> q.setQuiz(quiz));
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id " + id + " not found"));
    }

    @Override
    public Result submitQuiz(Long quizId, QuizSubmissionDTO submission) {

        Quiz quiz = getQuizById(quizId);

        if (resultRepository
            .findByQuizIdAndUserName(quizId, submission.getUserName())
            .isPresent()) {

            throw new InvalidSubmissionException("User has already attempted this quiz");
        }
        
        LocalDateTime startedAt = submission.getStartedAt();
        LocalDateTime submittedAt = LocalDateTime.now();

        if (startedAt == null || startedAt.isAfter(submittedAt)) {
            // throw new RuntimeException("Invalid quiz start time");
            throw new InvalidSubmissionException("Invalid quiz start time");
        }

        if (quiz.getDuration() <= 0) {
            // throw new RuntimeException("Invalid quiz duration");
            throw new InvalidSubmissionException("Invalid quiz duration");

        }

        LocalDateTime allowedEndTime =
            startedAt.plusMinutes(quiz.getDuration());

        boolean isLate = submittedAt.isAfter(allowedEndTime);

        int score = 0;
        if (!isLate) {
            for (Question q : quiz.getQuestions()) {
                String selected = submission.getAnswers().get(q.getId());
                if (q.getCorrectOption().equalsIgnoreCase(selected)) {
                    score++;
                }
            }
        }
        
        Result result = new Result();
        result.setQuiz(quiz);
        result.setUserName(submission.getUserName());
        result.setStartedAt(startedAt);
        result.setSubmittedAt(submittedAt);
        result.setLate(isLate);
        result.setScore(isLate ? 0 : score);
        result.setTotalQuestions(quiz.getQuestions().size());

        return resultRepository.save(result);
    }

@Override
public Result getMyResult(Long quizId, String userName) {
    return resultRepository
            .findByQuizIdAndUserName(quizId, userName)
            .orElseThrow(() ->
                new InvalidSubmissionException("Result not found"));
}
}