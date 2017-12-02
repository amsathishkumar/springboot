package com.sat.jersey.app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/sat")
public class JerseyRest {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloMessage() {
		return "Hello sat!";
	}
}





