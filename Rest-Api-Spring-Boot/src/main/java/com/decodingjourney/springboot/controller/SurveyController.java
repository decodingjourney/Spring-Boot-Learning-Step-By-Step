package com.decodingjourney.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.decodingjourney.springboot.model.Question;
import com.decodingjourney.springboot.service.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService service;
	
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retriveQuestionsForSurvey(@PathVariable String surveyId) {
		return service.retrieveQuestions(surveyId);
	}
	
	
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retriveQuestionForSurvey(@PathVariable String surveyId, @PathVariable String questionId) {
		return service.retrieveQuestion(surveyId, questionId);
	}
	// /surveys/{surveyId}/questions
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionsToSurvey(@PathVariable String surveyId,@RequestBody Question newQuestion) {
		Question question = service.addQuestion(surveyId, newQuestion);
		
		if (question == null) {
            return ResponseEntity.noContent().build();
        }
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();
		 return ResponseEntity.created(location).build();
		//return service.retrieveQuestions(surveyId);
		//What should be the structure
		//how will it be mapped to the Question Object
		// what will it return
		// what will be the response status
	}

}
