package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:26 on 05/12/2022
 */
@Data
public class ForgotPasswordRequest {

  @NotBlank
  private String username;
  @NotBlank
  private String email;
}

