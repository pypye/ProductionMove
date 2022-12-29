package com.example.productmoveapi.service.factory;

import com.example.productmoveapi.dto.request.static_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 11:24 on 29/12/2022
 */
public interface FactoryProductReportService {

  ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest);
}
