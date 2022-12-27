package com.example.productmoveapi.controller.internal.agency;

import com.example.productmoveapi.dto.request.static_request.SaleAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.agency.AgencySaleAnalysisService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 01:26 on 28/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/agency/sale/analysis")
@PreAuthorize("hasAuthority('agency')")
public class AgencySaleAnalysisController {

  private final AgencySaleAnalysisService agencySaleAnalysisService;

  public AgencySaleAnalysisController(AgencySaleAnalysisService agencySaleAnalysisService) {
    this.agencySaleAnalysisService = agencySaleAnalysisService;
  }

  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> saleAnalysis(
      @Valid @RequestBody SaleAnalysisRequest saleAnalysisRequest) {
    return agencySaleAnalysisService.saleAnalysis(saleAnalysisRequest);
  }
}