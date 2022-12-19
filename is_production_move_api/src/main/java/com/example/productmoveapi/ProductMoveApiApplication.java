package com.example.productmoveapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProductMoveApiApplication {

  @Value("${bc.strength}")
  public int strength;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(strength);
  }

  public static void main(String[] args) {
    SpringApplication.run(ProductMoveApiApplication.class, args);
  }

}
