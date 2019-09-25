package com.tcl.mid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
  /** variable created for the logger */
  private static Logger logger = LogManager.getLogger();

  public static void main(String[] args) {
    new App().configure(new SpringApplicationBuilder(App.class)).run(args);
    logger.info("Started");
  }
}
