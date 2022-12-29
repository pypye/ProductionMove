package com.example.productmoveapi.controller.internal.factory;

import com.example.productmoveapi.dto.request.static_request.StaticByStatusYearQuarterMonthRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.FactoryProductReportService;
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
 * @author Binh Nguyen Thai at 11:14 on 29/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/product/static")
@PreAuthorize("hasAuthority('factory')")
public class FactoryProductReportController {

  private final FactoryProductReportService factoryProductReportService;

  @Autowired
  public FactoryProductReportController(
      FactoryProductReportService factoryProductReportService) {
    this.factoryProductReportService = factoryProductReportService;
  }

  @GetMapping("/{statusId}/{option}")
  public ResponseEntity<GeneralResponse<Object>> getProductByStatusYearQuarterMonth(
      @PathVariable(name = "statusId") String statusId, @PathVariable(name = "option") String option) {
    return factoryProductReportService.getProductByStatusYearQuarterMonth(
        new StaticByStatusYearQuarterMonthRequest(statusId, option));
  }
}
