package com.serverseri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Main extends SpringBootServletInitializer {

  private static Logger logger = LoggerFactory.getLogger(Main.class);

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Main.class);
  }

  public static void main(String args[]) {
    logger.debug("Application Started");
    SpringApplication.run(Main.class, args);
    logger.debug("Application End");
  }
}
