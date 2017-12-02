package com.sat.jersey.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.sat.jersey.app.JerseyApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JerseyApplication.class )
public class JerseyApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
	@Test
	public void contextLoads() {
		 ResponseEntity<String> responseEntity =
		            restTemplate.getForEntity("/rest/sat/hello", String.class);
		        String client = responseEntity.getBody();
		        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		        assertEquals("Hello sat!", client);


	}
	
	

}

