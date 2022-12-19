package com.example.productmoveapi.dto.request;

/**
 * @author Binh Nguyen Thai at 23:02 on 28/11/2022
 */

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {

  @NotBlank
  @Pattern(message = "Invalid username", regexp = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")
  private String username;


  @NotBlank
  @Pattern(message = "Invalid password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$")
  private String password;
}
