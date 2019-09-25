package com.tcl.mid.fil;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tcl.mid.rep.JwtToken;
import com.tcl.mid.rep.JwtTokenRep;


@JWTSecure
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFil implements ContainerRequestFilter {
    private static Logger logger = LogManager.getLogger();

  /** Auto initialization of JWT token repository **/
  @Autowired
  private JwtTokenRep jwtTokenRepository;

  @Context
  private HttpServletRequest servlet;


  @Override
  public void filter(ContainerRequestContext requestContext)
      throws IOException {
    // Get the HTTP Authorization header from the request
    String authorizationHeader =
        requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
    // Check if the HTTP Authorization header is present and formatted correctly
    if (authorizationHeader == null
        || !authorizationHeader.startsWith("Bearer ")) {
      logger.warn("Authorization Header for JWT is missing in request");
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
          .entity("Authorization Header JWT is missing!!!")
          .type(MediaType.TEXT_PLAIN).build());
    }
    // Extract the token from the HTTP Authorization header
    System.out.println(authorizationHeader);
    String token = authorizationHeader.substring("Bearer".length()).trim();
    if (validateToken(token)) {
      // Validate the token
    } else { // Abort if the token validation fails
      requestContext
          .abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }

  private boolean validateToken(String token) {
    // Check if it was issued by the server and if it's not expired
    // Throw an Exception if the token is invalid
    Optional<JwtToken> jwtToken = jwtTokenRepository.findOneByToken(token);
    boolean retval = true;
    if (jwtToken.isPresent()) {
      Date createdDate = JwtTokenUtil.getCreatedDateFromToken(token);
      Date expirationDate = JwtTokenUtil.getExpirationDateFromToken(token);
      String ipAddr = JwtTokenUtil.getIpaddressFromToken(token);
      System.out.println(ipAddr);
      Date now = new Date();
      if (!ipAddr.contains(servlet.getRemoteAddr())) {
        retval = false;
      }
      if (createdDate == null || expirationDate == null) {
        retval = false;
      }
      if (createdDate.compareTo(now) > 0) { // checking the token creation date
        retval = false;
      }
      if (expirationDate.compareTo(now) <= 0) { // checking the token expiration
                                                // date
        retval = false;
      }
    } else { // return false if jwt not present in database
      retval = false;
    }
    return retval;
  }
}