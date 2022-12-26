package com.example.productmoveapi.dto.request.product_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 02:03 on 26/12/2022
 */
@Data
public class ChangeProductRequest {

  @NotBlank
  @Pattern(message = "Invalid code", regexp = "^[a-zA-Z0-9]*$")
  private String productCode;

  @NotBlank
  @Pattern(message = "Invalid code", regexp = "^[a-zA-Z0-9]*$")
  private String changedProductCode;
}
