package com.example.productmoveapi.dto.request.static_request;

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
  @Pattern(message = "Invalid year", regexp = "(^0$)|^(20(1[1-9]|[2-9][0-9]))$")
  private String year;

  @NotBlank
  @Pattern(message = "Invalid quarter", regexp = "^[0-4]$")
  private String quarter;

  @NotBlank
  @Pattern(message = "Invalid month", regexp = "^([0-9]|1[012])$")
  private String month;
}
