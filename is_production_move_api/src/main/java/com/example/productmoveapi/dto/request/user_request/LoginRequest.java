package com.example.productmoveapi.dto.request.user_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 23:02 on 28/11/2022
 */

@Data
public class LoginRequest {

  @NotBlank
  @Pattern(message = "Invalid username", regexp = "^[a-zA-Z0-9]*$")
  private String username;

  @NotBlank
  @Pattern(message = "Invalid password", regexp = "^[a-zA-Z0-9]*$")
  private String password;
}
