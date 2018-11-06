package com.decodingjourney.springboot.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.decodingjourney.springboot.Application;
import com.decodingjourney.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	
	@LocalServerPort
	private int port;

	@Test
	public void testRetrieveSurveyQuestion() {

		String retrieveSpecificQuestion = "/surveys/Survey1/questions/Question1";
		String url = createUrlWithPort(retrieveSpecificQuestion);

		TestRestTemplate restTemplate = new TestRestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.GET, entity, String.class);

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createUrlWithPort(String uri) {
		return "http://localhost:" + port
				+ uri;
	}
	
	@Test
    public void retrieveSurveyQuestions() throws Exception {
		TestRestTemplate template = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String retrieveAllQuestion = "/surveys/Survey1/questions/";
		ResponseEntity<List<Question>> response = template.exchange(
                createUrl(retrieveAllQuestion), HttpMethod.GET,
                new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
                new ParameterizedTypeReference<List<Question>>() {
                });

        Question sampleQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                        "India", "Russia", "United States", "China"));

        assertTrue(response.getBody().contains(sampleQuestion));
    }

	private String createUrl(String string) {
		// TODO Auto-generated method stub
		
		String localhost = "http://localhost:";
		return localhost+port+string;
	}
	
	@Test
	public void testAddQuestion() {

		String url = "http://localhost:" + port
				+ "/surveys/Survey1/questions";

		TestRestTemplate restTemplate = new TestRestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Question question = new Question("DOESNOTMATTER",
				"Without details", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));

		HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);

		ResponseEntity<String> response = restTemplate.exchange(url,
				HttpMethod.POST, entity, String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		System.out.println(actual);
		assertTrue(actual.contains("/surveys/Survey1/questions"));

	}

}
