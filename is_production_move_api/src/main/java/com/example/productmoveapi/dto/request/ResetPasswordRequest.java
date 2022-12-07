package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:27 on 05/12/2022
 */
@Data
public class ResetPasswordRequest {
  @NotBlank
  private String password;
  @NotBlank
  private String retypePassword;
  @NotBlank
  private String token;
}