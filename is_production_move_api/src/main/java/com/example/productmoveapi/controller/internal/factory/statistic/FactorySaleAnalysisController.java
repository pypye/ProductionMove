package com.example.productmoveapi.controller.internal.factory.statistic;

import com.example.productmoveapi.dto.request.statistic_request.SaleAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.statistic.FactorySaleAnalysisService;
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
 * @author Binh Nguyen Thai at 02:28 on 28/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/sale/analysis")
@PreAuthorize("hasAuthority('factory')")
public class FactorySaleAnalysisController {

  private final FactorySaleAnalysisService factorySaleAnalysisService;

  @Autowired
  public FactorySaleAnalysisController(
      FactorySaleAnalysisService factorySaleAnalysisService) {
    this.factorySaleAnalysisService = factorySaleAnalysisService;
  }

  /*
   * @description: Statistics and analysis of the number of products sold monthly, quarterly and annually.
   */
  @GetMapping("/{option:^[0-2]$}")
  public ResponseEntity<GeneralResponse<Object>> saleAnalysis(@PathVariable(name = "option") String option) {
    return factorySaleAnalysisService.saleAnalysis(new SaleAnalysisRequest(option));
  }
}
