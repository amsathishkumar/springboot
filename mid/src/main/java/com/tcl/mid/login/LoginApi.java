package com.tcl.mid.login;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcl.mid.fil.JWTSecure;
import com.tcl.mid.fil.JwtTokenUtil;
import com.tcl.mid.fil.Roles;
import com.tcl.mid.rep.JwtToken;
import com.tcl.mid.rep.JwtTokenRep;
;

@Component
@Path("/api")
@Consumes(MediaType.WILDCARD)
@Produces(MediaType.APPLICATION_JSON)
public class LoginApi {
  /** variable created for the logger */
  private static Logger logger = LogManager.getLogger();

  @Autowired
  private JwtTokenRep jwtTokenRepository;

  @Context
  UriInfo uriInfo;

  @Context
  /** variable for http servlet request */
  private HttpServletRequest servlet;

  @GET
  @Path("/read")
  @JWTSecure({Roles.ROLE_ADMI })
  public Response getMethod(@HeaderParam("Authorization") String authHeader
	    ) {
	  String token = authHeader.substring("Bearer".length()).trim();
      Optional<JwtToken> jwtToken = jwtTokenRepository.findOneByToken(token);
     	      return Response.status(Status.OK)
          .header("Authorization", "Bearer " + jwtToken.get().getToken())
          .entity(jwtToken.get().getUsername())
          .type(MediaType.APPLICATION_JSON_TYPE).build();


  }
  
  @POST
  @Path("/logout")
  @JWTSecure({Roles.ROLE_ADMI })
  public Response postDelete(@HeaderParam("Authorization") String authHeader
	    ) {
	  String token = authHeader.substring("Bearer".length()).trim();
      Optional<JwtToken> jwtToken = jwtTokenRepository.findOneByToken(token);
      if (jwtToken.isPresent()) {
        jwtTokenRepository.delete(jwtToken.get().getId());
      }
      return Response.status(Status.OK)
              .entity("jwt token removed from the repository successfully!!")
             .type(MediaType.TEXT_PLAIN).build();
  }
  @POST
  @Path("/login")
  public Response postLogin() {
    String uriPath = uriInfo.getPath();
    logger.info("URI Path for POST request: " + uriPath);
    MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
    System.out.println(queryParams.getFirst("name"));
    String token = JwtTokenUtil.generateToken(queryParams.getFirst("name"),  Roles.ROLE_ADMI, servlet.getRemoteAddr());
    // Create a new jwt token instance to be stored in the mongo repository
    JwtToken newToken = new JwtToken();
    // setting the token to be stored
    newToken.setToken(token);
    // setting the username to be stored with the token in db
    newToken.setUsername(queryParams.getFirst("name"));
    // setting the role of the user
    newToken.setRole(Roles.ROLE_ADMI);
    // setting the token creation date
    newToken.setCreatedDate(JwtTokenUtil.getCreatedDateFromToken(token));
    // setting the token expiration date
    newToken.setExpiryDate(JwtTokenUtil.getExpirationDateFromToken(token));
    // save the data to jwt mongo repository
    
    jwtTokenRepository.save(newToken);
    return Response.status(Status.OK)
            .entity(newToken.toString()).type(MediaType.TEXT_PLAIN)
            .build();
  }
}