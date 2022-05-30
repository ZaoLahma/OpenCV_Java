package com.github.zaolahma.webapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OpenCVWebApp {
  public static void main(String[] args) throws Exception {
	System.out.println("I'm starting damnit!");
    new SpringApplicationBuilder()
      .sources(OpenCVWebApp.class)
      .run(args);
  }
}
