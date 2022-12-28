package com.example.productmoveapi.controller.internal.factory;

import com.example.productmoveapi.dto.request.static_request.ErrorAnalysisRequest;
import com.example.productmoveapi.response.GeneralResponse;
import com.example.productmoveapi.service.factory.FactoryErrorAnalysisService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Binh Nguyen Thai at 03:02 on 28/12/2022
 */
@CrossOrigin(value = "*")
@Slf4j
@RestController
@RequestMapping("/factory/error/analysis")
@PreAuthorize("hasAuthority('factory')")
public class FactoryErrorAnalysisController {

  private final FactoryErrorAnalysisService factoryErrorAnalysisService;

  @Autowired
  public FactoryErrorAnalysisController(
      FactoryErrorAnalysisService factoryErrorAnalysisService) {
    this.factoryErrorAnalysisService = factoryErrorAnalysisService;
  }

  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> errorAnalysis(
      @Valid @RequestBody ErrorAnalysisRequest errorAnalysisRequest) {
    return factoryErrorAnalysisService.errorAnalysis(errorAnalysisRequest);
  }
}
