package com.example.quiz_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz_system.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}