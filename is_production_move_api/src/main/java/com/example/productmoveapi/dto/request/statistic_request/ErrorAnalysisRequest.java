package com.example.productmoveapi.dto.request.statistic_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 03:05 on 28/12/2022
 */
@Data
@AllArgsConstructor
public class ErrorAnalysisRequest {

  @NotBlank
  @Pattern(message = "Invalid category", regexp = "^[0-9]*$")
  private String category;

  @NotBlank
  @Pattern(message = "Invalid agency", regexp = "^[0-9]*$")
  private String agency;
}
