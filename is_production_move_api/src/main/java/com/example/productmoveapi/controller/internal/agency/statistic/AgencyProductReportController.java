package com.example.productmoveapi.controller.internal.agency.statistic;

import com.example.productmoveapi.dto.request.statistic_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.statistic.AgencyProductReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 19:56 on 29/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/product/statistic")
@PreAuthorize("hasAuthority('agency')")
public class AgencyProductReportController {

  private final AgencyProductReportService agencyProductReportService;

  @Autowired
  public AgencyProductReportController(
      AgencyProductReportService agencyProductReportService) {
    this.agencyProductReportService = agencyProductReportService;
  }

  /*
   * @description: Statistics and reports on product data by status, by month, quarter, year.
   */
  @GetMapping("/{statusId}/{option}")
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      @PathVariable(name = "statusId") String statusId, @PathVariable(name = "option") String option) {
    return agencyProductReportService.getProductByStatusYearQuarterMonth(
        new StaticByStatusYearQuarterMonthRequest(statusId, option));
  }
}
