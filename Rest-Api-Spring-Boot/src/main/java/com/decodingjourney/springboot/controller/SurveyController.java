package com.decodingjourney.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
