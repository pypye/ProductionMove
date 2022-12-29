package com.example.productmoveapi.dto.response.product_response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 03:45 on 28/12/2022
 */
@Data
@AllArgsConstructor
public class ErrorAnalysisResponse {

  private int numberOfErrorProduct;
  private int numberOfRestProduct;
}
