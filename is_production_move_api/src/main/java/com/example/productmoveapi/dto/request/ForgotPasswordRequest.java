package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:26 on 05/12/2022
 */
@Data
public class ForgotPasswordRequest {

  @NotBlank
  @Pattern(message = "Invalid username", regexp = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")
  private String username;

  @NotBlank
  @Pattern(message = "Invalid email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;
}

