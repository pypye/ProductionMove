package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:26 on 05/12/2022
 */
@Data
public class CreateAccountRequest {
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @NotBlank
  private String retypePassword;
  @NotBlank
  private  String displayName;
  @NotBlank
  private  String email;

}
