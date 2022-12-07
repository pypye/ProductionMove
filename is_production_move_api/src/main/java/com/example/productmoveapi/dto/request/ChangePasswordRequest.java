package com.example.productmoveapi.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:26 on 05/12/2022
 */
@Data
public class ChangePasswordRequest {
  @NotBlank
  private String oldPassword;
  @NotBlank
  private String newPassword;
  @NotBlank
  private String retypePassword;
}
