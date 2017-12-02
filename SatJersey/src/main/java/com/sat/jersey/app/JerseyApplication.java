package com.sat.jersey.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JerseyApplication {
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("started sathish");
        SpringApplication.run(JerseyApplication.class,args);
		 
	}
}
