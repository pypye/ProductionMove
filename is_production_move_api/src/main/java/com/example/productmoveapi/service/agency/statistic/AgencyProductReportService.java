package com.example.productmoveapi.service.agency.statistic;

import com.example.productmoveapi.dto.request.statistic_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author Binh Nguyen Thai at 20:05 on 29/12/2022
 */
public interface AgencyProductReportService {

  /*
   * @description: Statistics and reports on product data by type (interstate), by month, quarter, year.
   */
  ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      StaticByStatusYearQuarterMonthRequest staticByStatusYearQuarterMonthRequest);
}
