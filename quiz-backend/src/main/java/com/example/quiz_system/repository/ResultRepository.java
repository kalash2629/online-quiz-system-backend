package com.example.quiz_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz_system.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByQuizIdAndUserName(Long quizId, String userName);
}
