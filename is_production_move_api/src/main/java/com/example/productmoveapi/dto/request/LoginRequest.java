package com.example.productmoveapi.dto.request;

/**
 * @author Binh Nguyen Thai at 23:02 on 28/11/2022
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

  @NotBlank
  private String username;
  @NotBlank
  private String password;
}
