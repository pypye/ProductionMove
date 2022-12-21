package com.example.productmoveapi.dto.request.user_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 10:26 on 05/12/2022
 */
@Data
public class ChangePasswordRequest {

  @NotBlank
  @Pattern(message = "Invalid old password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{7,20}$")
  private String oldPassword;

  @NotBlank
  @Pattern(message = "Invalid new password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{7,20}$")
  private String newPassword;

  @NotBlank
  @Pattern(message = "Invalid confirmed password", regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{7,20}$")
  private String retypePassword;
}
