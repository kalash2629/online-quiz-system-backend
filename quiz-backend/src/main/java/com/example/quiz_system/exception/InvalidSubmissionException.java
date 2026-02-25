package com.example.quiz_system.exception;

public class InvalidSubmissionException extends RuntimeException {

    public InvalidSubmissionException(String message) {
        super(message);
    }
}