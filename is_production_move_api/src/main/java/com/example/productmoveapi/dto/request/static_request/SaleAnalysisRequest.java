package com.example.productmoveapi.dto.request.static_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 01:31 on 28/12/2022
 */
@Data
@AllArgsConstructor
public class SaleAnalysisRequest {

  @NotBlank
  @Pattern(message = "Invalid option", regexp = "^[0-2]$")
  private String option;
}
