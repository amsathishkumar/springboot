package com.sat.jersey.app.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.sat.jersey.app.rest.JerseyRest;


@Component
@ApplicationPath("/rest")
public class JerseyConf extends ResourceConfig {

	/**
	 * In constructor we can define Jersey Resources &amp; Other Components
	 */
	public JerseyConf() {
		register(JerseyRest.class);
	}
}