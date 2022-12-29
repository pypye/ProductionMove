package com.example.productmoveapi.service.warranty.statistic;

import com.example.productmoveapi.dto.request.statistic_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 18:53 on 29/12/2022
 */
public interface WarrantyProductReportService {

  ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest);
}
