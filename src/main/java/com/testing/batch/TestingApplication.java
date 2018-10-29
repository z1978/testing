package com.testing.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingApplication {
  
  private static final Logger LOGGER = LoggerFactory
      .getLogger(TestingApplication.class);

  private static final DateFormat DF = new SimpleDateFormat(
      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  public static void main(String[] args) throws Exception {
    
    long startTime=System.currentTimeMillis();
    System.out.println("==================================================");
    System.out.println("Start time = [" + DF.format(new Date()) + "]");
    System.out.println("==================================================");
    LOGGER.info("Start time = [" + DF.format(new Date()) + "]");
    
    SpringApplication.run(TestingApplication.class, args);
    
    LOGGER.info("End time = [" + DF.format(new Date()) + "]");
    long endTime = System.currentTimeMillis();
    System.out.println("==================================================");
    LOGGER.info("Program running time = [" + (endTime - startTime) + "]ms");
    System.out.println("Program running time = [" + (endTime - startTime)/1000f + "]s");
    System.out.println("==================================================");
    LOGGER.info("Program running time = [" + (endTime - startTime)/1000f + "]s");
  }
}