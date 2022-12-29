package com.example.productmoveapi.service.factory.statistic;

import com.example.productmoveapi.dto.request.statistic_request.ErrorAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 03:03 on 28/12/2022
 */
public interface FactoryErrorAnalysisService {

  /*
   * @description: Statistics on the percentage of error products by category, factory and warranty center.
   */
  ResponseEntity<GeneralResponse<Object>> errorAnalysis(ErrorAnalysisRequest errorAnalysisRequest);
}
