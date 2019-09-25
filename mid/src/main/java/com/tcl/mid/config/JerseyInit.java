package com.tcl.mid.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.tcl.mid.fil.AuthenticationFil;
import com.tcl.mid.fil.AuthorizationFil;
import com.tcl.mid.login.LoginApi;

@Component // Indicates this class as a springboot component
@ApplicationPath("/rest")
public class JerseyInit extends ResourceConfig {

  public JerseyInit() {

    register(AuthenticationFil.class);
    register(AuthorizationFil.class);
    register(LoginApi.class);
 
  }
}
