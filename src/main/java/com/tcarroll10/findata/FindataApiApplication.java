
package com.tcarroll10.findata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main driver for Finance data api service.
 * 
 * @author tom carroll
 * @version 2023-12-27
 */

@SpringBootApplication
public class FindataApiApplication {

  /**
   * This is the main method.
   * 
   * @param args Command-line arguments.
   */

  public static void main(String[] args) {
    SpringApplication.run(FindataApiApplication.class, args);
  }

}
