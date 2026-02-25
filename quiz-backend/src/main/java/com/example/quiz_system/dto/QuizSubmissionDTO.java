package com.example.quiz_system.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionDTO {

    private String userName;
    private Map<Long, String> answers;
    private LocalDateTime startedAt;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Map<Long, String> getAnswers() {
		return answers;
	}
	public void setAnswers(Map<Long, String> answers) {
		this.answers = answers;
	}
	public LocalDateTime getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}
}