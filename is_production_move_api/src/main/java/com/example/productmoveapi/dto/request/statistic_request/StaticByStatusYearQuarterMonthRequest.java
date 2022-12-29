package com.example.productmoveapi.dto.request.statistic_request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 16:57 on 27/12/2022
 */
@Data
@AllArgsConstructor
public class StaticByStatusYearQuarterMonthRequest {

  @NotBlank
  @Pattern(message = "Invalid status id", regexp = "^([1-9]|1[012])$")
  private String status;

  @NotBlank
  @Pattern(message = "Invalid option", regexp = "^[0-2]$")
  private String option;

}
