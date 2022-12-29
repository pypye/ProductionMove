package com.example.productmoveapi.dto.response.product_response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Binh Nguyen Thai at 20:50 on 29/12/2022
 */

@Data
@AllArgsConstructor
public class SaleAnalysisResponse {

  private Long numberOfProduct;
  private Long revenue;
}
