package com.example.productmoveapi.service.agency;

import com.example.productmoveapi.dto.request.static_request.AgencySaleAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 01:28 on 28/12/2022
 */
public interface AgencySaleAnalysisService {

  ResponseEntity<GeneralResponse<Object>> saleAnalysis(AgencySaleAnalysisRequest agencySaleAnalysisRequest);
}
