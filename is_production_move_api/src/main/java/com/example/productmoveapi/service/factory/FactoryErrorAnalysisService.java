package com.example.productmoveapi.service.factory;

import com.example.productmoveapi.dto.request.static_request.ErrorAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 03:03 on 28/12/2022
 */
public interface FactoryErrorAnalysisService {

  ResponseEntity<GeneralResponse<Object>> errorAnalysis(ErrorAnalysisRequest errorAnalysisRequest);
}
