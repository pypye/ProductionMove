package com.example.productmoveapi.service.agency.statistic;

import com.example.productmoveapi.dto.request.statistic_request.SaleAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 01:28 on 28/12/2022
 */
public interface AgencySaleAnalysisService {

  /*
   * @description: Statistics and analysis of the number of products sold monthly, quarterly and annually.
   */
  ResponseEntity<GeneralResponse<Object>> saleAnalysis(SaleAnalysisRequest saleAnalysisRequest);
}
