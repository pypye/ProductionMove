package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:27 on 05/12/2022
 */
@Data
public class ResetPasswordRequest {

  @NotBlank
  @Pattern(message = "Invalid password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$")
  private String password;

  @NotBlank
  @Pattern(message = "Invalid confirmed password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$")
  private String retypePassword;

  @NotBlank
  @Pattern(message = "Invalid token", regexp = "^[0-9]*$")
  private String token;
}
