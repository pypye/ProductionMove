package com.example.productmoveapi.service.factory.statistic;

import com.example.productmoveapi.dto.request.statistic_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 11:24 on 29/12/2022
 */
public interface FactoryProductReportService {

  /*
   * @description: Statistics and reports on product data by type (interstate), by month, quarter, year.
   */
  ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest);
}
