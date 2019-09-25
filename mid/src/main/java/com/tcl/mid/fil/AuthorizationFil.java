package com.tcl.mid.fil;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@JWTSecure
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFil implements ContainerRequestFilter {
  @Context
  private ResourceInfo resourceInfo;

  private static Logger logger = LogManager.getLogger();


  @Override
  public void filter(ContainerRequestContext requestContext)
  {
    Method resourceMethod = resourceInfo.getResourceMethod();
    List<Roles> methodRoles = extractRoles(resourceMethod);
    // Check if the user is allowed to execute the method
    // The method annotations override the class annotations
    if (methodRoles.isEmpty()) {
      // checkPermissions(classRoles, requestContext);
    } else {
      if (checkPermissions(methodRoles, requestContext)) {
      } else { // if no permission then abort
        requestContext
            .abortWith(Response.status(Response.Status.FORBIDDEN).build());
      }
    }
  }

  /**
   * Extract roles annotated to a method
   * 
   * @param annotatedElement element to extract roles
   * @return list of roles
   */
  // Extract the roles from the annotated element
  private List<Roles> extractRoles(AnnotatedElement annotatedElement) {
    if (annotatedElement == null) { // return empty array if no annotated
                                    // element
      return new ArrayList<Roles>();
    } else {
      JWTSecure jwtSecured = annotatedElement.getAnnotation(JWTSecure.class);
      if (jwtSecured == null) { // return empty array if jwt secured is null
        return new ArrayList<Roles>();
      } else {
        // return the list of allowed roles
        Roles[] allowedRoles = jwtSecured.value();
        return Arrays.asList(allowedRoles);
      }
    }
  }

  /**
   * Method to check the permission of the user to execute the REST API method
   * 
   * @param allowedRoles list of allowed roles
   * @param requestContext container request context
   * @return result of the check
   */
  private boolean checkPermissions(List<Roles> allowedRoles,
      ContainerRequestContext requestContext) {
    // Check if the user contains one of the allowed roles
    // Throw an Exception if the user has not permission to execute the method
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
    String token = authorizationHeader.substring("Bearer".length()).trim();
    Roles jwtRole = JwtTokenUtil.getRoleFromToken(token);
    if ((jwtRole != null) && (allowedRoles.contains(jwtRole))) {
      // if the role is part of allowed roles
      return true;
    } else {
      return false;
    }
  }
}